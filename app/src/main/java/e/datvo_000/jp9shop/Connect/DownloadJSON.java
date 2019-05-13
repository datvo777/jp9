package e.datvo_000.jp9shop.Connect;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by datvo_000 on 16/02/2019.
 */
//Params, Progress, Result
public class DownloadJSON extends AsyncTask<String,Void,String> {
    String duongdan;
    List<HashMap<String,String>> attrs;
    StringBuilder dulieu;
    String method ;


    public DownloadJSON(String duongdan){
        this.duongdan = duongdan;
        method = "GET";
    }

    public DownloadJSON(String duongdan, List<HashMap<String,String>> attrs){
        this.duongdan = duongdan;
        this.attrs = attrs;
        method = "POST";
    }
    public DownloadJSON(String duongdan, List<HashMap<String,String>> attrs,String method){
        this.duongdan = duongdan;
        this.attrs = attrs;
        this.method = method;
    }

    //xu li chinh bat bắt buộc phải dc gọi,ko thay đổi giao diện dc,vd:đang chạy toast lên..
    @Override
    protected String doInBackground(String... strings) {
        String data = "";
        String key = "";
        String value = "";
        try {



            if(method.equals("POST")){
                Uri.Builder builder = new Uri.Builder();

                int count = attrs.size();
                for(int i=0;i<count;i++){

                    for(Map.Entry<String,String> values : attrs.get(i).entrySet()){   //duyệt theo entry -lấy ra các entry trong map thứ i
                        key =  values.getKey().toString() ;
                        value =values.getValue().replace(" ","$") ;//The request is badly formed nếu truyền " "

                    }

                    builder.appendQueryParameter(key,value);
                }
                String query = builder.build().getQuery();
                URL url = new URL(duongdan+"?"+ query );

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//trả về 1 thể hiện (instance) của URLconnection - biểu thị 1 kết nối đến đối tượng được gọi bởi URL
                data = methodPost(httpURLConnection);
                Log.d("param", URLDecoder.decode(query,"UTF-8").toString());
            }
            else if (method.equals("GET")){
                URL url = new URL(duongdan);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                data = methodGet(httpURLConnection);
            }
            else if (method.equals("PUT"))
            {
                //Lớp trình trợ giúp để xây dựng hoặc thao tác các tham chiếu URI. vd: ?maSP=1&maKH=1
                Uri.Builder builder = new Uri.Builder();

                int count = attrs.size();
                for(int i=0;i<count;i++){

                    for(Map.Entry<String,String> values : attrs.get(i).entrySet())//duyệt theo entry -lấy ra các entry trong map thứ i
                    {
                        key = values.getKey() ;
                        value =  values.getValue().replace(" ","$");
                    }

                    builder.appendQueryParameter(key,value);//Mã hóa khóa và giá trị và sau đó nối thêm tham số vào chuỗi truy vấn.
                }
                String query = builder.build().getQuery();// trả về truy vấn được giải mã hoặc null nếu không có truy vấn
                Log.d("qery", duongdan+"?"+URLDecoder.decode(query,"UTF-8").toString());
                URL url = new URL(duongdan+"?"+query);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                data = methodPut(httpURLConnection);
            }
            else
            {
                Uri.Builder builder = new Uri.Builder();

                int count = attrs.size();
                for(int i=0;i<count;i++){

                    for(Map.Entry<String,String> values : attrs.get(i).entrySet())//duyệt theo entry -lấy ra các entry trong map thứ i
                    {
                        key = values.getKey() ;
                        value =  values.getValue();
                    }

                    builder.appendQueryParameter(key,value);
                }
                String query = builder.build().getQuery();
                Log.d("qery", duongdan+"?"+URLDecoder.decode(query,"UTF-8"));
                URL url = new URL(duongdan+"?"+query);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                data = methodDelete(httpURLConnection);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("dulieu",data);
        return data;
    }

    private String methodDelete(HttpURLConnection httpURLConnection) {
        String data = "";


        try {
            httpURLConnection.setRequestMethod("DELETE");

        } catch (ProtocolException e) {
            e.printStackTrace();
        }
      return   data = methodGet(httpURLConnection);
    }
    private String methodGet(HttpURLConnection httpURLConnection){
        String data = "";
        InputStream inputStream = null;
        try {
           httpURLConnection.connect();//Opens a communications link to the resource referenced by thisURL
                if (httpURLConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) { //<400

                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }
            InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(reader);

            dulieu = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) !=null){
                dulieu.append(line);
            }

            data = dulieu.toString();
            bufferedReader.close();
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private String methodPost(HttpURLConnection httpURLConnection){
        String data = "";

        try {
            httpURLConnection.setRequestMethod("POST");
            data = methodGet(httpURLConnection);

        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        return data;
    }
    private String methodPut(HttpURLConnection httpURLConnection){
        String data = "";
        try {
            httpURLConnection.setRequestMethod("PUT");
            data = methodGet(httpURLConnection);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}

