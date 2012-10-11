package com.sk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class  CreaterCSV {
	
	public static void CreateCsvfiles(String targetPath, String filename,
			List<ValueBean> fieldList, List<? extends Object> list){
		File file = null;
        OutputStream out = null;
        PrintWriter pw = null;
		try {

	        file = new File(targetPath + filename + ".csv");
            out = new FileOutputStream(file);
            pw = new PrintWriter(out);
           
			int fieldListLength = fieldList.size();
			int length = list.size();
			StringBuilder sb = new StringBuilder();
			 String[] fields = new String[fieldListLength];
			for(int i = 0 ;i < fieldListLength;i++){
				ValueBean vb = fieldList.get(i);
				sb.append(vb.getValue())
				   .append(",");
				fields[i] = vb.getKey();
			}
			sb.append("\n");
			Map<String,Method> methodMap = new HashMap<String, Method>();

			for (int i = 0; i < length; i++) {
				Object obj = list.get(i);
				for(int j = 0 ;j < fieldListLength; j++){
					String fieldName = fields[j];
					Method method = methodMap.get(fieldName);
					if(method == null){
						method = obj.getClass().getMethod(fieldName, new Class[] {});
						methodMap.put(fieldName, method);
					}
					
					sb.append(method.invoke(obj, new Object[]{}))
					  .append(",");
				}
				sb.append("\n");
			}
			pw.append(sb.toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
        	System.out.println(targetPath + filename + ".csv生成成功");
            try {
				pw.flush();
				pw.close();
				out.flush();
				out.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
        }
	}
	
	public static void CreateCsvfiles(String targetPath, String filename,
            String title[], List<JobBean> list) throws Exception {
        File file = null;
        OutputStream out = null;
        PrintWriter pw = null;
        try {
            file = new File(targetPath + filename + ".csv");
            out = new FileOutputStream(file);
            pw = new PrintWriter(out);
            for (int j = 0; j < title.length; j++) {
                pw.append(title[j] + ",");
            }
            for (int i = 0; i < list.size(); i++) {
                String operationname = (list.get(i).getPaerationname());
                String operationresult = (list.get(i).getOperationresult());
                String username = (list.get(i).getUsername());
                String userip = (list.get(i).getUereip());
                String operationtime = (list.get(i).getOperationtime());
                String operationdetail = (list.get(i).getOperationdetil());
                pw.append("\n");
                pw.append(operationname + ",");
                pw.append(operationresult + ",");
                pw.append(username + ",");
                pw.append(userip + ",");
                pw.append(operationtime + ",");
                pw.append(operationdetail + ",");
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
        	System.out.println(targetPath + filename + ".csv生成成功");
            pw.flush();
            pw.close();
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            String path = "e://";
            Date df = new Date();
            SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddHHmmss");
            String filename = "job_dump_" + sm.format(df);
            System.out.println(filename);
            List<JobBean> list = new ArrayList<JobBean>();
            for (int i = 0; i < 5; i++) {
                JobBean job = new JobBean();
                job.setPaerationname("beifen jin tai shu ju");
                job.setOperationresult("success");
                job.setUsername("admin");
                job.setOperationtime("20101-11-11 11:11:11");
                job.setUereip("10.71.138.167");
                job.setOperationdetil("danban");
                list.add(job);
            }
            CreaterCSV.CreateCsvfiles(path, filename, new String[]{"","","","","",""}, list);
//            CreaterCSV.CreateCsvfiles(path, filename, JobBean.getList(), list);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
