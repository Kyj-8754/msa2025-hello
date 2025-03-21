package project.cmd;

import java.io.*;
import java.text.*;
import java.util.*;

public class FileApp {

	private static String path = "c:/";
	private static Scanner sc = new Scanner(System.in);


	// 파일 확인
	private static boolean validFile(File file, String dir) {
		return  file.exists() && file.isFile() && file.getName().equalsIgnoreCase(dir);
	}
	// 폴더 확인
	private static boolean validFolder(File file, String dir) {
		return  file.exists() && file.isDirectory() && file.getName().equalsIgnoreCase(dir);
	}
	// 디렉토리 확인
	private static boolean validDirectory(File file, String dir) {
	return file.exists() && file.getName().equalsIgnoreCase(dir);
	}
	// 읽기 확인
	private static boolean validRead(File file) {
		return file.exists() && file.canRead();
	}
		
	
	// 디렉토리 파일 출력 명령문
	private static void dir_cmd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		DecimalFormat df = new DecimalFormat("#,###");

		File temp = new File(path);
		File[] contents = temp.listFiles();
		
		
		long total = 0;
		System.out.println("경로 " + path);
		System.out.println();
		for (File file : contents) {
			System.out.printf("%-25s", sdf.format(new Date(file.lastModified())));
			if (file.isDirectory()) {
				System.out.printf("%-10s%-10s%-20s", "<DIR>", "", file.getName());
			} else {
				System.out.printf("%-10s%-10s%-20s", "", df.format(file.length()), file.getName());
				total += file.length();
			}
			System.out.println();
		}
		System.out.printf("       %d개 파일   %s 바이트 \n", contents.length, df.format(total));
	}
		
	
	// 전체 파일 출력명령문
	private static void dirAll_cmd() {
		File temp = new File(path);
		File[] contents = temp.listFiles();

		dir_cmd();
		 if (contents != null) {
			for (File file : contents) {
		        if (file.isDirectory()) {
		            path = file.getPath();
		            dir_cmd();
		        }
		        path = file.getParent();
			}
		 }
	}
	

	// 디렉토리 이동 명령문
	private static void cd_cmd(String[] arg) {
		if (arg.length != 2) {
			System.out.println("사용법 : cd 이동할 경로");
			return;
		}
		File file = new File(path + arg[1]);
		if (validDirectory(file,arg[1])) {
			path += arg[1];
		} else {
			System.out.println(arg[0] + "> 해당 폴더는 존재하지 않습니다.");
		}
	}

	// 이름 다시 짓기 명령문
	private static void rename_cmd(String[] arg) {
		if (arg.length != 3) {
			System.out.println("사용법 : rename 기존 파일명 변경할 파일명");
			return;
		}

		File file = new File(path +"/"+ arg[1]);
		if (validFile(file,arg[1]) || validFolder(file,arg[1])) {
			file.renameTo(new File(path +"/"+arg[2]));
		} else {
			System.out.println(arg[1] + "이름의 해당 파일은 존재하지 않습니다.");
		}
	}

	
	//복사 명령문
	private static void copy_cmd(String[] arg) throws Exception {
		if (arg.length != 3) {
			System.out.println("사용법 : copy 원본 파일 복사 파일명");
			return;
		}
		System.out.println(path+"/"+arg[1]);
		File file = new File(path+"/"+arg[1]);
		File new_file = new File(path+"/"+arg[2]);
		
		if (validFile(file,arg[1])) {
			copy(file, new_file);
		}else if(validFolder(file,arg[1])) {
			copyDirectory(file, new_file);
		}
		else {
			System.out.println(arg[1] + "이름의 해당 파일은 존재하지 않습니다.");
		}
	}
	
	// 파일 읽기 명령문
		private static void type_cmd(String[] arg) throws Exception {
			if (arg.length != 2) {
				System.out.println("사용법 : type 찾고자 하는 파일명");
				return;
			}
			File file = new File(path+"/"+arg[1]);
			if (validRead(file)) {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				
				while (true) {
					String str = br.readLine();
					if (str == null)
						break;
					System.out.println(str);
				}
				br.close();
			} else {
				System.out.println(arg[1] + "이름의 해당 파일은 존재하지 않습니다.");
			}
		}
	
	// 순수 파일 복사 
		private static void copy(File dir, File new_dir) throws IOException{
			
			System.out.println("파일복사시작");
			try (FileInputStream fis = new FileInputStream(dir);
				     FileOutputStream fos = new FileOutputStream(new_dir)) {
				    byte[] buffer = new byte[4096];
				    int bytesRead;
				    while ((bytesRead = fis.read(buffer)) != -1) {
				        fos.write(buffer, 0, bytesRead);
				    }
				    fos.flush();
				}
			System.out.println("파일복사끝");
		}

		// 파일이 아닌 디렉토리일 경우 넘어가는 코드
		private static void copyDirectory(File dir, File new_dir) throws IOException {
	        File[] children = dir.listFiles();
	        if (children != null) {
	            for (File child : children) {
	                File destChild = new File(new_dir, child.getName());
	                if (child.isDirectory()) {
	                    destChild.mkdirs();
	                    copyDirectory(child, destChild);
	                } else {
	                    copy(child, destChild);
	                }
	            }
	        }
	    }
	

	public static void main(String[] args) throws Exception {
		while (true) {
			System.out.print(path + ">");
			String str = sc.nextLine();
			if ("exit".equals(str)) {
				// 프로그램 종료
				System.exit(0);
			}
			String[] arg = str.split(" ");
			switch (arg[0]) {
			case "cd": {
				// 이동하기
				cd_cmd(arg);
				break;
			}
			case "dir": {
				// 파일 내용 출력
				dir_cmd();
				break;
			}
			case "*": {
				// 파일 내용 출력
				dirAll_cmd();
				break;
			}
			case "rename": {
				// 파일 이름 다시 만들기
				rename_cmd(arg);
				break;
			}
			case "copy": {
				// 파일 카피
				copy_cmd(arg);
				break;
			}
			case "type": {
				// 타입 파일오픈해서 열기
				type_cmd(arg);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + arg[0]);
			}
			
		}
	}
}
