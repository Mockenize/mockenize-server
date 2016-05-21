package org.mockenize;

import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockenizeInfo {
	private static final Logger LOGGER = LoggerFactory.getLogger(MockenizeInfo.class);

	private static String version = null;

	private MockenizeInfo() {
	}

	public static final String getVersion() {
		try {
			if (version == null) {
				Manifest manifest = new Manifest(MockenizeInfo.class.getResourceAsStream("/META-INF/MANIFEST.MF"));
				Attributes attributes = manifest.getMainAttributes();
				version = attributes.getValue("Implementation-Version");
			}
		} catch (IOException ex) {
			LOGGER.error("Fail load version", ex);
		}
		return version;
	}

	public static String[] getAuthors() {
		return new String[] { "Mauricio Lima", "Rodrigo Watanabe" };
	}

}
