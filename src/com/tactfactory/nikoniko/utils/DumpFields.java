package com.tactfactory.nikoniko.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.tactfactory.nikoniko.models.modelbase.DatabaseItem;

public class DumpFields {
	public static <T> ArrayList<String> inspectBaseAttribut(Class<T> klazz) {
		ArrayList<String> attributs = new ArrayList<String>();
		Field[] fields;
		Class superClass = klazz;

		fields = superClass.getDeclaredFields();
		for (Field field : fields) {
			attributs.add(field.getName());
		}

		while (superClass.getSuperclass() != DatabaseItem.class
				&& superClass.getSuperclass() != Object.class) {
			superClass = superClass.getSuperclass();
			fields = superClass.getDeclaredFields();
			for (int i = fields.length - 1; i >= 0; i--) {
				attributs.add(0, fields[i].getName());
			}
		}

		return attributs;
	}

	public static <T> ArrayList<String> inspectGetter(Class<T> klazz) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			for (PropertyDescriptor propertyDescriptor : Introspector
					.getBeanInfo(klazz, DatabaseItem.class)
					.getPropertyDescriptors()) {

				result.add(propertyDescriptor.getReadMethod().getName());
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static <T> ArrayList<String> inspectSetter(Class<T> klazz) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			for (PropertyDescriptor propertyDescriptor : Introspector
					.getBeanInfo(klazz, DatabaseItem.class)
					.getPropertyDescriptors()) {

				result.add(propertyDescriptor.getWriteMethod().getName());
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static <T> ArrayList<Method> getGetter(Class<T> klazz) {
		ArrayList<Method> result = new ArrayList<Method>();
		try {
			for (PropertyDescriptor propertyDescriptor : Introspector
					.getBeanInfo(klazz, DatabaseItem.class)
					.getPropertyDescriptors()) {

				result.add(propertyDescriptor.getReadMethod());
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static <T> ArrayList<Method> getSetter(Class<T> klazz) {
		ArrayList<Method> result = new ArrayList<Method>();
		try {
			for (PropertyDescriptor propertyDescriptor : Introspector
					.getBeanInfo(klazz, DatabaseItem.class)
					.getPropertyDescriptors()) {

				result.add(propertyDescriptor.getWriteMethod());
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static Map<String, Object> fielder(Object bean) {
		try {
			return Arrays
					.asList(Introspector.getBeanInfo(bean.getClass(),
							Object.class).getPropertyDescriptors()).stream()
					// filter out properties with setters only
					.filter(pd -> Objects.nonNull(pd.getReadMethod()))
					.collect(Collectors.toMap(
					// bean property name
							PropertyDescriptor::getName, pd -> { // invoke
																	// method to
																	// get value
								try {
									Object test = pd.getReadMethod().invoke(bean);
									if (test == null) {
										test = "";
									}
									return test;
								} catch (Exception e) {
									// replace this with better error handling
									return e;
								}
							}));
		} catch (IntrospectionException e) {
			// and this, too
			return Collections.emptyMap();
		}
	}

	public static <T> ArrayList<Map<String, Object>> listFielder(List<T> items) {
		ArrayList<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (T item : items) {
			listMap.add(DumpFields.fielder(item));
		}
		return listMap;
	}

	/**
	 * Scans all classes accessible from the context class loader which belong
	 * to the given package and subpackages.
	 *
	 * @param packageName
	 *            The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<String> getClassesNames(String packageName)
			throws ClassNotFoundException, IOException {
		ArrayList<String> result = new ArrayList<String>();

		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}

		for (Class class1 : classes) {
			result.add(class1.getSimpleName().replace("ViewController", "")
					.toLowerCase());
		}
		return result;
	}

	/**
	 * Recursive method used to find all classes in a given directory and
	 * subdirs.
	 *
	 * @param directory
	 *            The base directory
	 * @param packageName
	 *            The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static List<Class> findClasses(File directory, String packageName)
			throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file,
						packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName
						+ '.'
						+ file.getName().substring(0,
								file.getName().length() - 6)));
			}
		}
		return classes;
	}

	public static <T> T createContentsWithId(Integer id, Class<T> clazz) {
		try {
			return clazz.getConstructor(Integer.class).newInstance(id);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T createContentsEmpty(Class<T> clazz) {
		try {
			return clazz.getConstructor().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
