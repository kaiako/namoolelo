package com.namoolelo.dbunit;

import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.github.springtestdbunit.dataset.DataSetLoader;

public class XLSDataSetLoader extends XlsDataFileLoader implements DataSetLoader{
	
	@Override
	public IDataSet loadDataSet(Class<?> testClass, String location)
		throws Exception {
		ResourceLoader resourceLoader = getResourceLoader(testClass);
		Resource resource = resourceLoader.getResource(location);
		if(resource.exists()){
			return loadDataSet(resource.getURL());
		}
		return null;		
	}

	private ResourceLoader getResourceLoader(Class<?> testClass) {
		return new ClassRelativeResourceLoader(testClass);
	}

}
