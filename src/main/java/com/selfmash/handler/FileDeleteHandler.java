package com.selfmash.handler;

import java.io.File;

import org.apache.log4j.Logger;

import com.selfmash.strings.Path;

public class FileDeleteHandler {

	private Logger logger = Logger.getLogger(getClass().getName());

	public void deleteFilePhoto(String fileName, String username) {
		try {
			new File(Path.PHOTO_PATH + "/" + username + "/" + fileName)
					.delete();
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage());
		}

	}
}
