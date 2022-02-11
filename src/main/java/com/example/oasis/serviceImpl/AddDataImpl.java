package com.example.oasis.serviceImpl;

import com.example.oasis.dao.AddDataMapper;
import com.example.oasis.po.Paper;
import com.example.oasis.service.AddDataInter;
import com.example.oasis.vo.PaperVO;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.datasource.DataSourceUtils;


@Service
@EnableScheduling
public class AddDataImpl implements AddDataInter {
    @Autowired
    AddDataMapper addDataMapper;

    @Autowired
    private GetDocument getDocument;


    private Logger logger = LoggerFactory.getLogger(AddDataImpl.class);

    @Override
    public int addData(String path) {
        if(path==null){
            return -1;
        }
        int count = 0;
        List<PaperVO> newList = readExcel(path);
        if(newList!=null) {

            for (int i = 0; i < newList.size(); i++) {
                String title = newList.get(i).getTitle();
                String authors = newList.get(i).getAuthors();
                List<Paper> indexList = addDataMapper.selectByTitleAndAuthors(title, authors);
                if (indexList.size() != 0) { //有重复的论文
                    continue;
                } else { //没有重复的论文，可以添加数据
                    String affiliations = newList.get(i).getAffiliations();
                    String publicTitle = newList.get(i).getPublicTitle();
                    int year = newList.get(i).getYear();
                    int sp = newList.get(i).getStartPage();
                    int ep = newList.get(i).getEndPage();
                    String abs = newList.get(i).getPaperAbstract();
                    String pdf = newList.get(i).getPdfLink();
                    String keyWord = newList.get(i).getAuthorKeyWord();
                    int ref = newList.get(i).getReferenceCount();
                    String pub = newList.get(i).getPublisher();
                    String docId = newList.get(i).getDocID();
                    count++;
                    addDataMapper.insertPaper(title, authors, affiliations, publicTitle,
                            year, sp, ep, abs, pdf, keyWord, ref, pub, docId);
                }
            }
            return count;
        }else{
            return -1;
        }
    }

    private List<PaperVO> readExcel(String filePath){
        try{
            List<PaperVO> list = new ArrayList<PaperVO>();
            InputStream is = new FileInputStream(filePath);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            int maxRow = sheet.getLastRowNum(); //获取总行数
            System.out.println("总行数：" + maxRow);
            for(int row = 0;row<=maxRow;row++){
                PaperVO info = new PaperVO();
                //获取总单元格数，这里从1开始计数
                int maxRol = sheet.getRow(row).getLastCellNum();
                //System.out.println("--------第" + row + "行的数据如下--------");
                //for(int rol = 0;rol<maxRol;rol++){
                    // System.out.println(sheet.getRow(row).getCell(rol));
                //}
                //System.out.println(sheet.getRow(row).getCell(5).toString());
                //System.out.println(sheet.getRow(row).getCell(5).toString().length());
                //System.out.println(sheet.getRow(row).getCell(maxRol-2).toString());
                //System.out.println(sheet.getRow(row).getCell(maxRol-1).toString());
                //System.out.println("--------------");
                if(!sheet.getRow(row).getCell(0).toString().equals("Keynotes")&&!sheet.getRow(row).getCell(0).toString().equals("Document Title")){
                    //是正常论文数据而不是标题
                    String title = sheet.getRow(row).getCell(0).toString();
                    String authors = sheet.getRow(row).getCell(1).toString();
                    String affiliations = sheet.getRow(row).getCell(2).toString();
                    String publicTitle = sheet.getRow(row).getCell(3).toString();
                    int year = Integer.parseInt(sheet.getRow(row).getCell(5).toString().substring(0,4));

                    String sp = sheet.getRow(row).getCell(8).toString();
                    String ep = sheet.getRow(row).getCell(9).toString();
                    if(Character.isDigit(sp.charAt(0)) && Character.isDigit(ep.charAt(0))&&sp.charAt(0)!='0'&&ep.charAt(0)!='0') {

                        int startPage = Integer.parseInt(sp.substring(0, sp.length() - 2));
                        int endPage = Integer.parseInt(ep.substring(0, ep.length() - 2));

                        String paperAbstract = sheet.getRow(row).getCell(10).toString();
                        String pdfLink = sheet.getRow(row).getCell(15).toString();
                        String authorKeyWord = "";
                        if (sheet.getRow(row).getCell(16) != null) {
                            authorKeyWord = sheet.getRow(row).getCell(16).toString();
                        }
                        int referenceCount = 0;
                        if(sheet.getRow(row).getCell(maxRol-7)!=null) {
                            String refer = sheet.getRow(row).getCell(maxRol - 7).toString();
                            referenceCount = Integer.parseInt(refer.substring(0, refer.length() - 2));
                        }
                        String publisher = sheet.getRow(row).getCell(maxRol - 2).toString();
                        String docID = sheet.getRow(row).getCell(maxRol - 1).toString();
                        info.setTitle(title);
                        info.setAuthors(authors);
                        info.setAffiliations(affiliations);
                        info.setPublicTitle(publicTitle);
                        info.setYear(year);
                        info.setStartPage(startPage);
                        info.setEndPage(endPage);
                        info.setPaperAbstract(paperAbstract);
                        info.setPdfLink(pdfLink);
                        info.setAuthorKeyWord(authorKeyWord);
                        info.setReferenceCount(referenceCount);
                        info.setPublisher(publisher);
                        info.setDocID(docID);
                        list.add(info);
                    }
                }
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private String driver = "com.mysql.cj.jdbc.Driver";
    private Connection conn = null;
    private int pageNum = 71;  //测试跑到了第70页，当前搜索条件论文数远大于二百页

    /*/
        UpdateTime： 2020. 7 . 24
        方法作为定时方法，定时爬虫并插入数据库
     */
    @Scheduled(cron = "0 0 0 */1 * ?")  //每天凌晨0点执行一次
    @Lazy(true)
    private void addFormSpider(){
        ArrayList<Paper> Paperlist = getDocument.Begin(pageNum);
        logger.info("已爬取页号: " + pageNum);
        int res = 0;
        if(Paperlist.size()>0){
            res = InsertPaper(Paperlist);
        }
        pageNum++;
        logger.info("插入数据条数: " + res);
        if(pageNum/10 == 0){  //每爬取十页，更新一次
            UpdateTable updateTable = new UpdateTable();
            updateTable.updateAffiliation();
            updateTable.updateAffiliation_link();
            updateTable.updateAuthor_Affiliation_link();
            updateTable.updateAuthorPaper();
            updateTable.updateAuthorPic();

            updateTable.updateConference();
            updateTable.updateNode();
            updateTable.updateLink();

            updateTable.updatefieldPaper();  //更新顺序很重要
            updateTable.updateResearch_Field();
            updateTable.updatefieldAuthor();

            updateTable.updatefieldAffiliation();


        }
    }


    public int InsertPaper(ArrayList<Paper> list){
        int count = 0;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection
                    ("jdbc:mysql://114.55.92.12:3306/newxyzzdb?useSSL=FALSE&serverTimezone=UTC", "root", "root");
            conn.setAutoCommit(false);
            PreparedStatement prep = conn.prepareStatement("INSERT IGNORE into `newxyzzdb`.paper value (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            for(Paper paper:list){
                prep.setInt(1,0);
                prep.setString(2,paper.getTitle());
                prep.setString(3,paper.getAuthors());
                prep.setString(4,paper.getAffiliations());
                prep.setString(5,paper.getPublicTitle());
                prep.setInt(6,paper.getYear());
                prep.setInt(7,paper.getStartPage());
                prep.setInt(8,paper.getEndPage());
                prep.setString(9,paper.getPaperAbstract());
                prep.setString(10,paper.getPdfLink());
                prep.setString(11,paper.getAuthorKeyWord());
                prep.setInt(12,paper.getReferenceCount());
                prep.setString(13,paper.getPublisher());
                prep.setString(14,paper.getDocID());
                prep.addBatch();
                count++;
            }
            prep.executeBatch();//批处理操作， 一次加入多条数据
            conn.commit();
            prep.close();
        }catch (Exception e){
            try{
                conn.rollback();
                e.printStackTrace();
            }catch(SQLException e1){
                e1.printStackTrace();
            }
        }finally{
            try{
                conn.close();
            }catch (SQLException e2){
                e2.printStackTrace();
            }
        }
        return count;
    }
}
