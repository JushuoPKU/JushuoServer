package nlp;
import org.fnlp.app.keyword.AbstractExtractor;
import org.fnlp.app.keyword.WordExtract;
import org.fnlp.nlp.cn.CNFactory;
import org.fnlp.nlp.cn.tag.CWSTagger;
import org.fnlp.nlp.corpus.StopWords;

import java.util.Map;

/**
 * Created by MebiuW on 16/5/19.
 */
public class Test {
    public static void main(String args[]) throws  Exception{
        // 创建中文处理工厂对象，并使用“models”目录下的模型文件初始化
        String path="/Users/MebiuW/Documents/Doing/PKU/JushuoServer/lib/fundannlp/models";
        CNFactory factory = CNFactory.getInstance(path);

        // 使用分词器对中文句子进行分词，得到分词结果
        String input="ARM今天正式宣布，他们和台积电联手研发的10nm工艺的芯片已经完成，这亦成为了全球第一块基于10nm工艺的芯片。其实这块芯片应该在2015年的12月的时候就要完成，不过拖了这么久也就说明了10nm工艺具有一定的研发难度。而这块芯片如无意外应该使用ARM尚未宣布的旗舰级架构，代号“Artemis”";
        String[] words = factory.seg(input);

        // 打印分词结果
        for(String word : words) {
            System.out.print(word + " ");
        }
        System.out.println();

        StopWords sw= new StopWords(path+"/stopwords");
        CWSTagger seg = new CWSTagger(path+"/seg.m");
        AbstractExtractor key = new WordExtract(seg,sw);

        //you need to set the keywords number, here you will get 10 keywords
        Map<String,Integer> ans = key.extract(input, 10);
        System.out.println(ans.toString());
    }
}
