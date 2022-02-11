## -*- coding: utf-8 -*-
from selenium import webdriver
from bs4 import BeautifulSoup
import time
import re
import requests
import json

USER_AGENT = 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 ' \
             'Safari/537.36 '
chrome_driver=r"src\main\resources\Driver\geckodriver.exe"
browser = webdriver.Firefox(executable_path=chrome_driver)
# 驱动chrome。必须要安装对应浏览器版本的driver，并给出其所在路径。同样，在python的安装目录下也需要有这个driver。根据自己的浏览器版本自行下载

def get_reference(url, link_num):
    headers = {"Connection": "close", "Accept": "application/json, text/plain, */*", "cache-http-response": "true",
               "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36",
               "Referer": "https://ieeexplore.ieee.org/document/" + link_num + "/references",
               "Accept-Encoding": "gzip, deflate",
               "Accept-Language": "zh-CN,zh;q=0.9,en;q=0.8"}
    res = requests.get(url, headers=headers)
    content = json.loads(res.text)
    if 'references' in content:
        reference = content["references"]
    else:
        reference = list()
    return reference


def isContainClass(allClass,targetClass): #照搬的，未改动
    #解析allClass,判断是否包含targetClass
    classArr = allClass.split(' ')
    result = False
    for str in classArr:
        if str == targetClass:
            result = True
            break
    return result

def ieee_info(url):
    headers = {'User-Agent': USER_AGENT}
    res = requests.get(url, headers=headers)

    pattern = re.compile('metadata={.*};')
    content = json.loads(pattern.search(res.text).group()[9:-1])
    #print(len(content))

    #从所有内容中抓取需要的元素
    if 'title' in content:
        title = content['title']
    else:
        title = ''

    if 'authors' in content:
        #authors = content['authors']
        authors = get_authors(content['authors'])
        affiliations=get_affiliations(content['authors'])
    else:
        authors = list()
        affiliations = list()

    if 'abstract' in content:
        abstract = content['abstract']
    else:
        abstract = ''

    if 'publicationTitle' in content:
        publication = content['publicationTitle']
    else:
        publication = ''

    if 'publicationYear' in content:
        year = content['publicationYear']
    else:
        year = ''

    if 'startPage' in content:
        startPage = content['startPage']
    else:
        startPage = ''

    if 'endPage' in content:
        endPage = content['endPage']
    else:
        endPage = ''

    if 'keywords' in content:
        #print(content['keywords'])
        keywords = get_keywords(content['keywords'])
    else:
        keywords = list()

    if 'publisher' in content:
        publisher = content['publisher']
    else:
        publisher = ''

    if 'subType' in content:
        docID = content['subType']
    else:
        docID = ''

    if 'doi' in content:
        doi = content['doi']
    else:
        doi = ''

    paper = dict(
        title=title,
        authors=authors,
        affiliations=affiliations,
        abstract=abstract,
        publication=publication,
        year=year,
        startPage=startPage,
        endPage=endPage,
        keywords=keywords,
        publisher=publisher,
        docID=docID,
        doi=doi
    )
    return paper

#调整作者，论文，作者关键字的格式
def get_keywords(keywords):
    for kds in keywords:
        if "type" in kds:
            if kds["type"] == 'Author Keywords ':
                print(kds["kwd"])
                return kds["kwd"]
    return ['NA']

def get_authors(authors):
    res=[]
    for aus in authors:
        res.append(aus['name'])
    return res

def get_affiliations(authors):
    res = []
    for afs in authors:
        if(afs['affiliation']==''):
            res.append('NA')
        else:
            res.append(afs['affiliation'])
    return res


def get_result():

    result = []#结果返回集
    count = 0
    for i in range(1, 2):#自行设定要爬多少页
        base_path = "C:/Users/apple/Desktop/" # 路径自己设置，此处以桌面为例，改为x m x 的桌面


        ase_res = open(base_path + 'ase_res3.json', 'a')
        #暂时写进一个json文件保存，如果结果不写入文件，这两句代码可删

        #网址，自行选择需要爬的网页，这里只是一个示例
        url1 = "https://ieeexplore.ieee.org/search/searchresult.jsp?queryText=SLAM&sortType=desc_p_Citation_Count&PageNumber=1&highlight=true&returnType=SEARCH&matchPubs=true&pageNumber="+str(i)+"&ranges=2014_2018_Year&returnFacets=ALL"
        browser.get(url1)
        print(url1)
        time.sleep(5)# 等一下
        linkNums = []
        link_list = browser.find_elements_by_xpath("//*[@data-artnum]")  # 使用selenium的xpath定位到每个具有data-artnum元素
        for link in link_list:
            if isContainClass(link.get_attribute('className'), 'icon-pdf'):
                ele_num = link.get_attribute('data-artnum')
                print(ele_num)
                linkNums.append(ele_num)

        for link_num in linkNums:
            url = "https://ieeexplore.ieee.org/document/" + link_num #论文页面的连接
            print(url)
            paper = ieee_info(url)
            ref_url = "https://ieeexplore.ieee.org/rest/document/" + link_num + "/references" #论文引文页面的链接
            ref = get_reference(ref_url, link_num)
            single = dict()

            #获得数据库需要的每一列数据
            single['title'] = u''.join(paper['title']).encode('utf-8').strip().decode() # 照搬助教原爬虫代码，其实不用这么写也行
            single['authors'] = '; '.join(paper['authors']).encode('utf-8').strip().decode()
            single['affiliations'] = '; '.join(paper['affiliations']).encode('utf-8').strip().decode()
            single['publicTitle'] = u''.join(paper['publication']).encode('utf-8').strip().decode()
            single['year'] = paper['year'].strip()
            single['startPage'] = paper['startPage'].strip()
            single['endPage'] = paper['endPage'].strip()
            single['paperAbstract'] = u''.join(paper['abstract']).encode('utf-8').strip().decode()
            single['pdfLink'] = "https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=" + link_num
            single['authorKeyWord'] = u', '.join(paper['keywords']).strip()
            single['referenceCount'] = len(ref)
            single['publisher'] = paper['publisher'].strip()
            single['docID'] = paper['docID'].strip()

            result.append(single)
            count = count + 1
            if(count==3):
                break
            #ase_res.write(json.dumps(single) + '\n')
            #ase_res.flush()
    return result

    #ase_res.close()
    #  写入，然后关闭文件。如无需写入文件则删除这三句代码


