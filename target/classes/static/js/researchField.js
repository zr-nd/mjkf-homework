$(document).ready(function(){
    var field=localStorage.getItem("authorKeyWord");
    var author_count=localStorage.getItem("author_count");
    var paper_count=localStorage.getItem("paper_count");
    var reference_count=localStorage.getItem("reference_count");
    var paperList;

    $("#one").text(field);
    $("#two").text(field+" has "+author_count+" authors, "+paper_count+" papers and "+reference_count+" citations.");
    $("#three").text("You can get some information about top authors in "+field+" here.");
    $("#four").text("Authors: "+author_count);
    $("#five").text("Papers: "+paper_count);
    $("#six").text("Citations: "+reference_count);

    document.getElementById('topauthor').style.width="620px";
    document.getElementById('topauthor').style.height="420px";
    document.getElementById('topaffiliation').style.width="620px";
    document.getElementById('topaffiliation').style.height="420px";
    getTopAuthor();
    getTopAffiliation();

    $.ajax({
        type: 'POST',
        url: '/topPaperinField',
        async: true,
        data: JSON.stringify(field),
        contentType: 'application/json',
        processData: false,
        success: function (res) {
            paperList=res;
            var txt="";
            for(var i=0;i<paperList.length;i++){
                txt=txt+"<tr>"+
                    "<td id='titleInfo-"+i+"'>"+"<a class='title' href='detailByTitle'>"+paperList[i].title+"</a></td>"+
                    "<td id='authorsInfo-"+i+"'>";

                var authorList=paperList[i].authors.split(";");
                txt=txt+"<a class='author' href='authorDetail'>"+authorList[0]+"</a>";
                for(var j=1;j<authorList.length;j++){
                    txt=txt+" "+"<a class='author' href='authorDetail'>"+authorList[j]+"</a>";
                }

                txt=txt+"</td>"+
                    "<td>"+paperList[i].referenceCount+"</td>"+
                    "</tr>";

            }
            document.getElementById('seven').innerHTML=txt;
        },
        error: function (error) {
            alert("failure" + error);
        }
    })

    $("#seven").on('click','.author',function(){
        var author=$(this).text();
        if (author.charAt(0)==' '){
            author=author.substring(1,author.length);
        }
        var paperId=$(this).parent().attr('id').replace('authors','title');
        var paperTitle=$("#"+paperId).text();
        //alert(author);
        //alert(paperTitle);
        $.ajax({
            type:'POST',
            url:'/authorPic/byNameAndTitle',
            async:false,
            data:JSON.stringify({
                'author':author,
                'title':paperTitle
            }),
            dataType:'json',
            contentType:'application/json',
            processData:false,
            success:function(res){
                localStorage.setItem("author",res.author);
                localStorage.setItem("affiliation",res.affiliation);
                localStorage.setItem("authorPapers",res.papers);
                localStorage.setItem("refs",res.refs);
                window.location.href="authorDetail";
            },
            error:function(error){
                alert("错误"+JSON.stringify(error));
            }
        })
        //alert($(this).parent().attr('id'));
    })

    $("#seven").on('click','.title',function(){
        var title=$(this).text();
        for(var i=0;i<paperList.length;i++){
            if(paperList[i].title==title){
                localStorage.setItem('dtitle',paperList[i].title);
                localStorage.setItem('dauthors',paperList[i].authors);
                localStorage.setItem('daffiliations',paperList[i].affiliations);
                localStorage.setItem('dpublicTitle',paperList[i].publicTitle);
                localStorage.setItem('dyear',paperList[i].year);
                localStorage.setItem('dstartPage',paperList[i].startPage);
                localStorage.setItem('dendPage',paperList[i].endPage);
                localStorage.setItem('dpdfLink',paperList[i].pdfLink);
                localStorage.setItem('dauthorKeyWord',paperList[i].authorKeyWord);
                localStorage.setItem('dreferenceCount',paperList[i].referenceCount);
                localStorage.setItem('dpublisher',paperList[i].publisher);
                localStorage.setItem('ddocID',paperList[i].docID);
                localStorage.setItem('dpaperAbstract',paperList[i].paperAbstract);
                break;
            }
        }
        window.location.href="detailByTitle";
    })

    var axisLabel1 = {
        // 方法2：换行显示
        interval: 0, //强制全部显示，1表示隔一个；2隔两个
        formatter: function (params) {
            var newParamsName = ""; // 最终拼接成的字符串
            var paramsNameNumber = params.length; // 实际标签的个数
            var provideNumber = 8; // 每行能显示的字的个数
            var rowNumber = Math.ceil(paramsNameNumber / provideNumber); // 换行的话，需要显示几行，向上取整
            /**
             * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签
             */
            // 条件等同于rowNumber>1
            if (paramsNameNumber > provideNumber) {
                /** 循环每一行,p表示行 */
                for (var p = 0; p < rowNumber; p++) {
                    var tempStr = ""; // 表示每一次截取的字符串
                    var start = p * provideNumber; // 开始截取的位置
                    var end = start + provideNumber; // 结束截取的位置
                    // 此处特殊处理最后一行的索引值
                    if (p == rowNumber - 1) {
                        // 最后一次不换行
                        tempStr = params.substring(start, paramsNameNumber);
                    } else {
                        // 每一次拼接字符串并换行
                        tempStr = params.substring(start, end) + "\n";
                    }
                    newParamsName += tempStr; // 最终拼成的字符串
                }

            } else {
                // 将旧标签的值赋给新标签
                newParamsName = params;
            }
            //将最终的字符串返回
            return newParamsName;
        },
    };

    function getTopAuthor(){
        $.ajax({
            type:'POST',
            url : '/topAuthorinField',
            asnyc:true,
            data:JSON.stringify(field),
            contentType: 'application/json',
            processData:false,
            success:function (res) {

                var nameList = res.map(function (item) {
                    return item.author;
                });
                var activeList = res.map(function (item) {
                    return item.active;
                });
                var option = {
                    label:{
                        show:true,//开启显示
                        position:'top'
                    },
                    color:['#3398DB'],
                    /*tooltip: {
                        trigger:'axis',
                        axisPointer:{
                            type:'shadow'
                        }
                    },*/
                    grid:{
                        left:"6%", //距离边框左部
                        right:'6%',
                        bottom:"10%", //距离边框底部
                        //containLabel: true
                    },
                    title : {
                        text: '最活跃的作者',
                        textStyle:{
                            fontWeight: 'bold',
                        },
                        x:'center'
                    },
                    xAxis:{
                        type:'category',
                        data:nameList,
                        axisLabel:axisLabel1,
                        fontSize:1
                    },
                    yAxis:{
                        type:'value'
                    },
                    series:[{
                        data:activeList,
                        type:'bar',
                        name:'活跃度',
                        barGap:40
                    }]
                };
                var authorChart = echarts.init($("#topauthor")[0]);
                authorChart.setOption(option);
            },
            error:function (res) {
                alert(res);
            }
        })
    }

    function getTopAffiliation(){
        $.ajax({
            type:'POST',
            url : '/topAffiliationinField',
            asnyc:true,
            data:JSON.stringify(field),
            contentType: 'application/json',
            processData:false,
            success:function (res) {

                var affiliationList = res.map(function (item) {
                    return item.name;
                });
                var activeList = res.map(function (item) {
                    return item.active;
                });

                var option = {
                    label:{
                        show:true,//开启显示
                        position:'right'
                    },
                    color:['#3398DB'],
                    title:{
                        text:'最活跃的机构',
                        left:'center'
                    },
                    tooltip:{
                        trigger:'axis',
                        axisPointer:{type:'shadow'}
                    },
                    legend:{
                        data:'活跃度'
                    },
                    grid:{
                        left:'6%',
                        right:'6%',
                        bottom:'5%',
                        containLabel:true
                    },
                    xAxis: {
                        type:'value',
                        boundaryGap:[0,0.1]
                    },
                    yAxis: {
                        type:'category',
                        data:affiliationList.reverse(),
                        axisLabel : {
                            formatter: function(){
                                return "";
                            }
                        }
                    },
                    series:[{
                        name:'活跃度',
                        type:'bar',
                        data:activeList.reverse(),
                        barGap: 40
                    }]
                };
                var affiliationChart = echarts.init($("#topaffiliation")[0]);
                affiliationChart.setOption(option);
            },
            error:function (res) {
                alert(res);
            }
        })
    }

})