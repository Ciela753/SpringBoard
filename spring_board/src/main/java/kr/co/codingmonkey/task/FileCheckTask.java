package kr.co.codingmonkey.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;

import kr.co.codingmonkey.controller.UploadController;
import kr.co.codingmonkey.domain.AttachVo;
import kr.co.codingmonkey.mapper.AttachMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Component @Log4j @AllArgsConstructor
public class FileCheckTask {
	private AttachMapper attachMapper;
	
	private Set<String> getFolderBefore(List<AttachVo> fileList) {
		return new HashSet(fileList.stream().map(vo->vo.getPath()).collect(Collectors.toSet()));
	}
	
	
	
//	@Scheduled(cron="0 * * * * *")
	public void checkFiles() {
		log.warn("file check task run..................");
		log.warn("========================================");
		
//		attachMapper.getOldFiles().forEach(log::info);
		
		//DB list
		List<AttachVo> fileList = attachMapper.getOldFiles();
		if(fileList == null) return;
		
		List<Path> fileListPaths = 
				fileList
				.stream()
				.map(vo -> 
					Paths.get(UploadController.getUploadFolder(), vo.getFullPath()))
				.collect(Collectors.toList());
		
		
		log.warn("==================fileList======================");
		fileListPaths.forEach(log::info);
		//thumbnail  여부
		
		fileList
		.stream()
		.filter(vo -> vo.isImage())
		.map(vo -> Paths.get(UploadController.getUploadFolder(), vo.getThumb()))
		.forEach(fileListPaths::add);
		
		log.warn("=================FilelistPaths=======================");
		fileListPaths.forEach(log::info);
		
		//물리적 파일 list
		
		log.warn("============fileList======================");
		getFolderBefore(fileList).forEach(log::info);
		
		getFolderBefore(fileList).forEach(folder -> {
			File targetDir = Paths.get(UploadController.getUploadFolder(), folder).toFile();
			log.warn("===================removesFiles====================");
			File[] removeFiles = targetDir.listFiles(file->!fileListPaths.contains(file.toPath()));
			
		});
		
	}
}
