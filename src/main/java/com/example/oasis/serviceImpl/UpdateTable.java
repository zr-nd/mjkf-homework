package com.example.oasis.serviceImpl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class UpdateTable {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private Connection conn = null;

    private Logger logger = LoggerFactory.getLogger(UpdateTable.class);

    public int updateAffiliation(){
        String deleteSql = "delete from affiliation;";
        String insertSql = "insert into affiliation select A.name,papers,authors,refs from (select a.affiliation as name,count(*) as papers,sum(a.referenceCount) as refs from(select affiliation,referenceCount from author_affiliation_link group by P_Id,affiliation,referenceCount) a group by affiliation) A , (select affiliation as name,count(*) as authors from(select affiliation,author from author_affiliation_link group by affiliation,author) a group by affiliation) B where A.name=B.name;";
        int res = operateTable(deleteSql,insertSql);
        if(res > 0){
            System.out.println("affiliation建表成功并填充数据");
        }else{
            logger.error("affiliation建表失败");
        }
        return res;
    }

    public int updateAffiliation_link(){
        String deleteSql = "delete from affiliation_link;";
        String insertSql = "insert into affiliation_link select * from (select distinct A.affiliation as source,B.affiliation as target from author_affiliation_link A, author_affiliation_link B where A.P_Id=B.P_Id and A.affiliation<>B.affiliation) a where a.source in (select name from node where category=1) and a.target in (select name from node where category=1);";
        int res = operateTable(deleteSql,insertSql);
        if(res > 0){
            System.out.println("affiliation_link建表成功 且 插入数据");
        }else{
            logger.error("更新affiliation_link出错");
        }
        return res;
    }

    public int updateAuthor_Affiliation_link(){
        String deleteSql = "delete from author_affiliation_link;";
        String insertSql = "insert into author_affiliation_link select P_Id, trim(substring_index(substring_index(a.authors,';',b.help_topic_id+1),';',-1)) as author,trim(substring_index(substring_index(a.affiliations,';',b.help_topic_id+1),';',-1)) as affiliation,referenceCount from paper a join\n" +
                "                                                                                                                                                                                                                                                                 mysql.help_topic b on  b.help_topic_id < (length(a.affiliations) - length(replace(a.affiliations,';',''))+1) order by a.P_Id;";
        int res = operateTable(deleteSql,insertSql);
        if(res > 0){
            System.out.println("更新author_affiliation_link表成功");
        }else{
            logger.error("更新author_affiliation_link表失败");
        }
        return res;
    }

    public int updateAuthorPic(){
        String deleteSql = "delete from authorpic;";
        String insertSql = "insert into authorpic(author,affiliation,papers,refs) select t.author,t.affiliation,count(t.author) as papers,sum(t.referenceCount) as refs from(select distinct p.P_Id,p.referenceCount,substring_index(substring_index(p.authors,'; ',b.help_topic_id+1),'; ',-1) author,substring_index(substring_index(p.affiliations,'; ',b.help_topic_id+1),'; ',-1) affiliation,authorKeyWord from paper p join mysql.help_topic b on b.help_topic_id < (length(p.authors)-length(replace(p.authors,'; ',''))+1) where p.authors<>'')as t group by t.author,t.affiliation order by papers desc;";
        int res = operateTable(deleteSql,insertSql);
        if(res>0){
            System.out.println("authorpic建表成功并成功填充数据。");
        }
        return res;
    }

    public int updateAuthorPaper(){
        String deleteSql = "delete from authorpaper;";

        String insertSql = "insert into authorpaper(author,affiliation,title,refs) select distinct substring_index(substring_index(p.authors,'; ',b.help_topic_id+1),'; ',-1) author,substring_index(substring_index(p.affiliations,'; ',b.help_topic_id+1),'; ',-1) affiliation,p.title,p.referenceCount from paper p join mysql.help_topic b on b.help_topic_id < (length(p.authors)-length(replace(p.authors,'; ',''))+1) where p.authors<>'';";
        int res = operateTable(deleteSql,insertSql);
        if(res>0){
            System.out.println("authorPaper建表成功并成功填充数据。");
        }
        return res;

    }

    public int updateConference(){
        String deleteSql = "delete from conference;";

        String insertSql = "insert into conference(name,paperCount,referCount) select publicTitle as conference ,count(publicTitle) as paperCount,sum(referenceCount) from paper group by publicTitle order by paperCount desc;";
        int res = operateTable(deleteSql,insertSql);
        if(res>0){
            System.out.println("conference建表成功并成功填充数据。");
        }
        return res;
    }


    public int updateNode(){
        String deleteSql = "delete from node;";

        String insertSql = "insert into node (select 0 as category,concat(author,'--',affiliation) as name,0.7*papers+0.3*refs as value,0.7*papers+0.3*refs as symbolSize,concat(author,'--',affiliation) as label from authorpic order by value desc limit 20) union (select 1 as category,name,0.5*authors+0.3*papers+0.2*refs as value,0.5*authors+0.3*papers+0.2*refs as symbolSize, name as label from affiliation order by value desc limit 1,100)\n" +
                "union (select 2 as category,authorKeyWord as name,0.5*author_count+0.3*paper_count+0.2*referenceCount as value,0.5*author_count+0.3*paper_count+0.2*referenceCount as symbolSize,authorKeyWord as label from research_field order by value desc limit 20);";
        int res = operateTable(deleteSql,insertSql);
        if(res > 0){
            System.out.println("node建表成功");
        }else{
            logger.error("node 建表失败");
        }
        return res;
    }

    public int updateLink(){
        String deleteSql = "delete from link;";
        String insertSql = "insert into link select name as source, substring_index(name,'-',-1) as target from node where category=0\n" +
                "union select name as source,concat(author,'--',affiliation) as target from node A, fieldauthor B where A.category=2 and A.name=B.authorKeyWord and  concat(author,'--',affiliation) in (select name from node where category=0)\n" +
                "union select A.name as source,authorKeyWord as target from node A, fieldaffiliation B where A.category=1 and A.name=B.name and B.authorKeyWord in (select name from node where category=2);";
        int res = operateTable(deleteSql,insertSql);
        if(res > 0){
            System.out.println("link 建表成功");
        }else{
            logger.error("link 建表失败");
        }
        return res;
    }

    public int updateResearch_Field(){
        String deleteSql = "delete from research_field;";
        String insertSql = "insert into research_field(authorKeyWord, author_count, paper_count, referenceCount)\n" +
                "select * from(\n" +
                "    select authorKeyWord,sum(length(authors)-length(replace(authors,';',''))+1)as author_count,count(title) as paper_count,referenceCount\n" +
                "    from fieldpaper group by authorKeyWord order by authorKeyWord) x;";
        int res = operateTable(deleteSql,insertSql);
        if(res > 0){
            System.out.println("Research_Field 建表成功");
        }else{
            logger.error("Research_Field 建表失败");
        }
        return res;
    }

    public int updatefieldPaper(){
        String deleteSql = "delete from fieldpaper;";
        String insertSql = "insert into fieldpaper(authorKeyWord, referenceCount, authors, title)\n" +
                "select * from(\n" +
                "select substring_index(substring_index(y.authorKeyWord,';',b.P_Id),';',-1) as authorKeyWord,y.referenceCount,y.authors,y.title from\n" +
                "(select substring_index(substring_index(x.authorKeyWord,', ',b.P_Id),', ',-1) as authorKeyWord,x.referenceCount,x.authors,x.title\n" +
                "from (select substring_index(substring_index(a.authorKeyWord,'; ',b.P_Id),'; ',-1) as authorKeyWord,a.referenceCount,a.authors,a.title\n" +
                "      from paper a join paper b on b.P_Id<(length(a.authorKeyWord)-length(replace(a.authorKeyWord,'; ',' '))+2)) x\n" +
                "    join paper b on b.P_Id<(length(x.authorKeyWord)-length(replace(x.authorKeyWord,', ',' '))+2)) y\n" +
                "    join paper b on b.P_Id<(length(y.authorKeyWord)-length(replace(y.authorKeyWord,';',''))+2)) q where length(authorKeyWord)>0;";
        int res = operateTable(deleteSql,insertSql);
        if(res > 0){
            System.out.println("fieldpaper 建表成功");
        }else{
            logger.error("fieldpaper 建表失败");
        }
        return res;
    }

    public int updatefieldAuthor(){
        String deleteSql = "delete from fieldauthor;";
        String insertSql = "insert into fieldauthor(authorKeyWord, author, paper_count, referenceCount, affiliation)\n" +
                "select authorKeyWord,author,count(title) as paper_count,sum(referenceCount) as referenceCount,affiliation from(\n" +
                "select x.authorKeyWord,x.author,a.affiliation,x.title,x.referenceCount from (select a.authorKeyWord,a.referenceCount,substring_index(substring_index(a.authors,'; ',b.P_Id),'; ',-1) as author,a.title\n" +
                "from fieldpaper a join paper b on b.P_Id<(length(a.authors)-length(replace(a.authors,'; ',' '))+2)) x, authorpaper a where x.author=a.author\n" +
                "and x.title=a.title) y group by authorKeyWord,author,affiliation,referenceCount,title;";
        int res = operateTable(deleteSql,insertSql);
        if(res > 0){
            System.out.println("fieldauthor 建表成功");
        }else{
            logger.error("fieldauthor 建表失败");
        }
        return res;
    }

    public int updatefieldAffiliation(){
        String deleteSql = "delete from fieldaffiliation;";
        String insertSql = "insert into fieldaffiliation(authorKeyWord, name, author_count, referenceCount, paper_count)\n" +
                "select authorKeyWord,name,sum(author_count) as author_count,sum(refs) as referenceCount,count(title) as paper_count from(\n" +
                "select a.authorKeyWord,b.affiliation as name,count(b.author) as author_count,b.refs as refs,b.title\n" +
                "from fieldpaper a,authorpaper b where b.title=a.title group by a.authorKeyWord, b.affiliation,b.title) x group by authorKeyWord, name;";
        int res = operateTable(deleteSql,insertSql);
        if(res > 0){
            System.out.println("fieldaffiliation 建表成功");
        }else{
            logger.error("fieldaffiliation 建表失败");
        }
        return res;
    }



    private int operateTable(String deleteSql, String insertSql){
        int res = 0;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection
                    ("jdbc:mysql://114.55.92.12:3306/newxyzzdb?useSSL=FALSE&serverTimezone=UTC", "root", "root");
            conn.setAutoCommit(false); //设置不能自动提交
            Statement stmt = conn.createStatement();
            stmt.execute(deleteSql);
            res = stmt.executeUpdate(insertSql);
            conn.commit();  //提交事务，释放锁
            stmt.close();
            return res;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
            try{
                conn.rollback();
                conn.setAutoCommit(true);
            } catch(SQLException e1){
                e1.printStackTrace();
                logger.error(e1.toString() + "回滚出错!");
            }
        }finally {
            try{
                conn.close();
            }catch (SQLException e2){
                e2.printStackTrace();
            }
        }
        return res;
    }
}
