/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.PrintWriter;

public class Assignment{

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.bbc.com/urdu").get();
        String string[] = {"https://www.bbc.com/urdu/topics/cjgn7n9zzq7t","https://www.bbc.com/urdu/topics/cl8l9mveql2t", "https://www.bbc.com/urdu/topics/cw57v2pmll9t", "https://www.bbc.com/urdu/topics/c340q0p2585t", "https://www.bbc.com/urdu/topics/ckdxnx900n5t"};
        String array[] = {"Pakistan", "Around", "World", "Game", "The Artist"};
        List<String> real_data = new ArrayList<>();
        // = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < string.length; i++) {
            count = 0;
            String page = " ";
            int p = 1;
            //outer:
            for (int infinite = 0; infinite == 0 && count != 100;) {
                Document content = Jsoup.connect(string[i] + page).get();
                Elements contentclass = content.getElementsByClass("bbc-uk8dsi emimjbx0");
                for (Element e : contentclass) {
                    count++;
                    String address = e.attr("href");
                    Document datalink = Jsoup.connect(address).get();
                    Elements data = datalink.getElementsByClass("bbc-4wucq3 essoxwk0");
                    String s = data.text();
                    real_data.add(array[i] + "," + s);
                    if (count == 100) {
                        break;
                    }
                }
                p++;
                page = "?page=";
                page = page + p;
            }
        }//convertind data to csv file
        try {
            PrintWriter pw = new PrintWriter(new File("C:\\Users\\DELL\\Desktop\\3rd smstr\\books_table.csv"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < real_data.size(); i++) {
                sb.append(real_data.get(i));
                sb.append("\r\n");
            }
            pw.write(sb.toString());
            pw.close();
            System.out.println("finished");
        } catch (Exception e) {
            // TODO: handle exception
        }
       // fetch(real_data);
    }
}
