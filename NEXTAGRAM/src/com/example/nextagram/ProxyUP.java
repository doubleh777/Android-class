package com.example.nextagram; 

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import android.util.Log;
 
public class ProxyUP {
       
 
        private String lineEnd = "\r\n";
        private String twoHyphens = "--";
        private String boundary = "*****";
        private String server_URL = "http://192.168.0.7:5009/";
        
        
        public String deleteComment(int articleNumber, int commentNumber){
        	try {
               
                URL url = new URL(server_URL + "deleteComment");
               
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
               
                conn.setRequestProperty("Accept-Charset", "UTF-8");
               
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Cache-Control", "no-cache");
                conn.setRequestProperty("Connection", "Keep-Alive");
               
                //응답 데이터가 많을 때에는 일정양 씩 묶어서 보내겠다는 의미
                conn.setRequestProperty("Transfer-Encoding", "chunked");
               
                conn.setDoOutput(true);
                conn.setDoInput(true);

                //Content는 multipart형식이고 데이터의 끝을 boundary(*****)로 표시를 하겠다.(content-length이 없으므로...)
                conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);

                // write data
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
               
               
                dos.write( getPostData("articleNumber",""+articleNumber).getBytes("UTF-8") );
                dos.write( getPostData("commentNumber",""+commentNumber).getBytes("UTF-8") );
                
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // close streams
                Log.i("Test", "Comment is deleted");
               
                dos.flush();
                dos.close();
               
                int status = conn.getResponseCode();
                Log.i("test", "statusUP:" + status);
               
                switch (status) {
                case 200:
                case 201:
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                                sb.append(line + "\n");
                        }
                        br.close();
                        return sb.toString();
                }

        	} catch (Exception e) {
        			e.printStackTrace();
                	Log.i("test", "COMMENT DELETE ERROR:" + e);
        	}
       
        	return null;
        }
        public String uploadComment(Comment comment){
        	try {
               
        		URL url = new URL(server_URL + "uploadComment");
        		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                
                conn.setRequestProperty("Accept-Charset", "UTF-8");
               
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Cache-Control", "no-cache");
                conn.setRequestProperty("Connection", "Keep-Alive");
               
                //응답 데이터가 많을 때에는 일정양 씩 묶어서 보내겠다는 의미
                conn.setRequestProperty("Transfer-Encoding", "chunked");
               
                conn.setDoOutput(true);
                conn.setDoInput(true);

                //Content는 multipart형식이고 데이터의 끝을 boundary(*****)로 표시를 하겠다.(content-length이 없으므로...)
                conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);

                // write data
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
               
                dos.write( getPostData("articleNumber",""+comment.getArticleNumber()).getBytes("UTF-8") );
                dos.write( getPostData("commentWriter",comment.getCommentWriter()).getBytes("UTF-8") );
                dos.write( getPostData("commentDate",comment.getCommentDate()).getBytes("UTF-8") );
                dos.write( getPostData("comment",comment.getComment()).getBytes("UTF-8") );
                                
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                
                // close streams
                Log.i("Test", "Comment is written");
               
                dos.flush();
                dos.close();
                
                int status = conn.getResponseCode();
                Log.i("test", "statusUP:" + status);
              
                switch (status) {
                case 200:
                case 201:
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                                sb.append(line + "\n");
                        }
                        br.close();
                        return sb.toString();
                       
             
                		
                
                }

        	} catch (Exception e) {
        			e.printStackTrace();
                	Log.i("test", "UPLOAD ERROR:" + e);
        	}
       
        	return null;
        }
       
        public String uploadArticle(Article article, String filePath) {
               
                // 안드로이드 베이직 수업에서 자세히 다룰 내용입니다.
                // 네트워크 선행이 필요한 이유입니다.
               
                try {
                       
                        FileInputStream fis = new FileInputStream(filePath);
                       
                        URL url = new URL(server_URL + "upload");
                       
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                       
                        conn.setRequestProperty("Accept-Charset", "UTF-8");
                       
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Cache-Control", "no-cache");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                       
                        //응답 데이터가 많을 때에는 일정양 씩 묶어서 보내겠다는 의미
                        conn.setRequestProperty("Transfer-Encoding", "chunked");
                       
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
 
                        //Content는 multipart형식이고 데이터의 끝을 boundary(*****)로 표시를 하겠다.(content-length이 없으므로...)
                        conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);
 
                        // write data
                        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                       
                       
                        dos.write( getPostData("title",article.getTitle()).getBytes("UTF-8") );
                        dos.write( getPostData("writer",article.getWriter()).getBytes("UTF-8") );
                        dos.write( getPostData("id",article.getId()).getBytes("UTF-8") );
                        dos.write( getPostData("content",article.getContent()).getBytes("UTF-8") );
                        dos.write( getPostData("writeDate",article.getWriteDate()).getBytes("UTF-8") );
                        dos.write( getPostData("imgName",article.getImgName()).getBytes("UTF-8") );
                   
                   
                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + article.getImgName() + "\"" + lineEnd);
                        dos.writeBytes(lineEnd);
 
                       
                        int bytesAvailable = fis.available();
                        int maxBufferSize = 1024;
                       
                        // 버퍼사이즈 조정
                        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
 
                        byte[] buffer = new byte[bufferSize];
                        int bytesRead = fis.read(buffer, 0, bufferSize);
 
                        // read image
                        while (bytesRead > 0) {
                                dos.write(buffer, 0, bufferSize);
                                bytesAvailable = fis.available();
                                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                                bytesRead = fis.read(buffer, 0, bufferSize);
                        }
 
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
 
                        // close streams
                        Log.i("Test", "File is written");
                       
                        fis.close();
                        dos.flush();
                        dos.close();
                       
                        int status = conn.getResponseCode();
                        Log.i("test", "statusUP:" + status);
                       
                        switch (status) {
                        case 200:
                        case 201:
                                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                StringBuilder sb = new StringBuilder();
                                String line;
                                while ((line = br.readLine()) != null) {
                                        sb.append(line + "\n");
                                }
                                br.close();
                                return sb.toString();
                        }
 
                } catch (Exception e) {
                        e.printStackTrace();
                        Log.i("test", "UPLOAD ERROR:" + e);
                }
               
                return null;
        }
       
        // post방식에 맞게 데이터 형식을 추가
        private String getPostData(String key, String value) {
                String result = twoHyphens + boundary + lineEnd;
                result += "Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd;
                result += lineEnd;
               
                result += value;
               
                result += lineEnd;
               
                return result;
        }
       
}