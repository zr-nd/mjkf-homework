$(document).ready(function () {

    var publisher="IEEE";
    // var country="All";
    var startYear=2010;
    var endYear=2019;
    var field="All";
    var conference="All";
    // var firstAuthor=true;
    var isAffiliation=true;
    var attribute="papers";
    var currentPage=1;
    var pageNum;

    rank();

    $('#nextPage').click(function(){
        if(currentPage==1){
            document.getElementById("firstPage").setAttribute("class","");
            document.getElementById("prePage").setAttribute("class","");
        }
        if(currentPage==pageNum-1){
            document.getElementById("nextPage").setAttribute("class","hide");
            document.getElementById("lastPage").setAttribute("class","hide");
        }
        currentPage++;
        rank();
    })

    $("#prePage").click(function(){
        if(currentPage==2){
            document.getElementById("firstPage").setAttribute("class","hide");
            document.getElementById("prePage").setAttribute("class","hide");
        }
        if(currentPage==pageNum){
            document.getElementById("nextPage").setAttribute("class","");
            document.getElementById("lastPage").setAttribute("class","");
        }
        currentPage--;
        rank();
    })

    $("#firstPage").click(function(){
        currentPage=1;
        document.getElementById("firstPage").setAttribute("class","hide");
        document.getElementById("prePage").setAttribute("class","hide");
        document.getElementById("nextPage").setAttribute("class","");
        document.getElementById("lastPage").setAttribute("class","");
        rank();
    })

    $("#lastPage").click(function(){
        currentPage=pageNum;
        document.getElementById("firstPage").setAttribute("class","");
        document.getElementById("prePage").setAttribute("class","");
        document.getElementById("nextPage").setAttribute("class","hide");
        document.getElementById("lastPage").setAttribute("class","hide");
        rank();
    })

    function changePage(){
        currentPage=1;
        pageNum=0;
        document.getElementById("firstPage").setAttribute("class","hide");
        document.getElementById("prePage").setAttribute("class","hide");
        document.getElementById("nextPage").setAttribute("class","");
        document.getElementById("lastPage").setAttribute("class","");
    }

    function rank(){
        var rankMap={};
        var data={};
        rankMap['publisher']=publisher;
        // rankMap['country']=country;
        rankMap['startYear']=startYear;
        rankMap['endYear']=endYear;
        rankMap['field']=field;
        rankMap['conference']=conference;
        rankMap['attribute']=attribute;
        // rankMap['firstAuthor']=firstAuthor;
        data['startYear']=startYear;
        data['endYear']=endYear;

        $("#sk-three-bounce").show();
        $("#ranking").html("");

        if(isAffiliation) {
            $.ajax({
                type: 'POST',
                url: '/rankAffiliation',
                asnyc: true,
                data: JSON.stringify(rankMap),
                contentType: 'application/json',
                processData: false,
                success: function (res) {
                    if(res.length==0){
                        alert("搜索结果为空");
                        $("#ranking").html("");
                        $("#sk-three-bounce").hide();
                    }
                    else {
                        var text = "";
                        pageNum = Math.ceil(res.length / 50);
                        //$("#ranking").html("");
                        for (var i = currentPage * 50 - 50; i < currentPage * 50; i++) {
                            if (i >= res.length)
                                break;

                            //var text = "";
                            data['name'] = res[i].name;
                            text = text + "<tr class='item'>" +
                                "<td>" + "<img src='/images/z2.ico' style='height:30px;width:30px'/>" + (i + 1) + ". " + res[i].name + "</td>" +
                                "<td>" + res[i].papers + "</td>" +
                                "<td>" + res[i].refs + "</td>" +
                                "<td><div id='trend" + (i + 1) + "'></div></td>" +
                                "</tr>";

                            //$("#ranking").append(text);
                            getPublicTrend1(data, i);
                        }
                        $("#ranking").append(text);
                        $("#sk-three-bounce").hide();
                    }
                },
                error: function (res) {

                }

            })
        }
        else{
            $.ajax({
                type: 'POST',
                url: '/rankAuthor',
                asnyc: true,
                data: JSON.stringify(rankMap),
                contentType: 'application/json',
                processData: false,
                success: function (res) {

                    var text = "";
                    pageNum=Math.ceil(res.length/50);

                    //$("#ranking").html("");
                    for (var i = currentPage*50-50; i < currentPage*50; i++) {
                        if(i>=res.length)
                            break;

                        data['name'] = res[i].name;
                        //var text="";
                        text = text + "<tr class='item'>" +
                            "<td><img src='/images/person.png' style='height:30px;width:30px'/>" + (i + 1) + ". " + res[i].name + "</td>" +
                            "<td>" + res[i].papers + "</td>" +
                            "<td>" + res[i].refs + "</td>" +
                            "<td><div id='trend" + (i + 1) + "'></div></td>" +
                            "</tr>";

                        //$("#ranking").append(text);
                        getPublicTrend2(data,i);
                    }
                    $("#ranking").append(text);
                    $("#sk-three-bounce").hide();

                },
                error: function (res) {

                }

            })
        }
    }

    function getPublicTrend1(data,i) {
        $.ajax({
            type: 'POST',
            url: '/publicTrend1',
            asnyc: true,
            data: JSON.stringify(data),
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                var count = res.map(function (item) {
                    return item.count;
                });
                var year = res.map(function (item) {
                    return item.year;
                });
                var yearList = new Array(endYear - startYear + 1);
                var countList = new Array(endYear - startYear + 1);
                for (var k = startYear; k <= endYear; k++) {
                    yearList[k - startYear] = k;
                    countList[k - startYear] = 0;
                }

                for (var k = 0; k < year.length; k++) {
                    countList[year[k] - startYear] = count[k];
                }

                var option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {type: 'shadow'}
                    },
                    xAxis: {
                        type: 'category',
                        data: yearList,
                        axisLabel: {
                            formatter: function () {
                                return "";
                            }
                        },
                        axisTick: {
                            show: false
                        },
                    },
                    yAxis: {
                        type: 'value',
                        splitLine: {show: false},
                        axisTick: { //y轴刻度线
                            show: false
                        },
                        axisLine: { //y轴
                            show: false
                        },
                        axisLabel: {
                            formatter: function () {
                                return "";
                            }
                        },
                        scale: true,
                        max: 16,
                        min: 0,
                        splitNumber: 4
                    },
                    series: [{
                        data: countList,
                        type: 'bar',
                        name: 'publication count',
                        itemStyle: {
                            normal: {
                                color: function (params) {
                                    var colorList = ['#C33531', '#EFE42A', '#64BD3D', '#EE9201', '#29AAE3', '#B74AE5', '#0AAF9F', '#E89589', '#16A085', '#4A235A', '#C39BD3', '#F9E79F', '#BA4A00', '#ECF0F1', '#616A6B', '#EAF2F8', '#4A235A', '#3498DB'];
                                    return colorList[params.dataIndex]
                                }
                            }
                        }
                    }]
                };
                document.getElementById("trend" + (i + 1)).style.width = "167px";
                document.getElementById("trend" + (i + 1)).style.height = "85px";

                var trendChart = echarts.init(document.getElementById("trend" + (i + 1)));
                trendChart.setOption(option);

            },
            error: function (res) {

            }
        })
    }

    function getPublicTrend2(data,i){
        $.ajax({
            type:'POST',
            url: '/publicTrend2',
            asnyc: true,
            data: JSON.stringify(data),
            contentType: 'application/json',
            processData: false,
            success:function(res){
                var count = res.map(function (item) {
                    return item.count;
                });
                var year = res.map(function (item) {
                    return item.year;
                });
                var yearList=new Array(endYear-startYear+1);
                var countList=new Array(endYear-startYear+1);
                for(var k=startYear;k<=endYear;k++) {
                    yearList[k-startYear]=k;
                    countList[k-startYear]=0;
                }

                for(var k=0;k<year.length;k++){
                    countList[year[k]-startYear]=count[k];
                }

                var option = {
                    tooltip:{
                        trigger:'axis',
                        axisPointer:{type:'shadow'}
                    },
                    xAxis:{
                        type:'category',
                        data:yearList,
                        axisLabel : {
                            formatter: function(){
                                return "";
                            }
                        },
                        axisTick:{
                            show:false
                        },
                    },
                    yAxis:{
                        type:'value',
                        splitLine:{show:false},
                        axisTick:{ //y轴刻度线
                            show:false
                        },
                        axisLine:{ //y轴
                            show:false
                        },
                        axisLabel : {
                            formatter: function(){
                                return "";
                            }
                        },
                        scale:true,
                        max:8,
                        min:0,
                        splitNumber:4
                    },
                    series:[{
                        data:countList,
                        type:'bar',
                        name:'publication count',
                        itemStyle:{
                            normal:{
                                color: function(params) {
                                    var colorList = ['#C33531','#EFE42A','#64BD3D','#EE9201','#29AAE3', '#B74AE5','#0AAF9F','#E89589','#16A085','#4A235A','#C39BD3','#F9E79F','#BA4A00','#ECF0F1','#616A6B','#EAF2F8','#4A235A','#3498DB' ];
                                    return colorList[params.dataIndex]
                                }
                            }
                        }
                    }]
                };
                document.getElementById("trend"+(i+1)).style.width="167px";
                document.getElementById("trend"+(i+1)).style.height="85px";

                var trendChart=echarts.init(document.getElementById("trend"+(i+1)));
                trendChart.setOption(option);

            },
            error:function(res){

            }
        })

    }


    $("#publisher li button").click(function(){
        $("#publisher li").each(function(){
            $(this).children().removeClass("active");
        })
        $(this).addClass("active");
        publisher=$(this).text();
        changePage();
        rank();
    })

    // $("#country li .single").click(function(){
    //     $("#country li").each(function(){
    //         $(this).children().removeClass("active");
    //     })
    //     $(this).addClass("active");
    //     country=$(this).text();
    //     if(country=="中国"){
    //         country="China";
    //     }
    //     else if(country=="美国"){
    //         country="USA";
    //     }
    //     else if(country=="日本"){
    //         country="Japen";
    //     }
    //     else if(country=="欧洲"){
    //         country="Europe";
    //     }
    //     changePage();
    //     rank();
    // })
    //
    // $("#countriesList").change(function(){
    //     $("#country li").each(function(){
    //         $(this).children().removeClass("active");
    //     })
    //     $("#selectCountries").addClass("active");
    //     country=$(this).val();
    //     alert(country);
    // });

    // $("#firstAuthor li button").click(function(){
    //     $("#firstAuthor li").each(function(){
    //         $(this).children().removeClass("active");
    //     });
    //     $(this).addClass("active");
    //     if($(this).text()=="是"){
    //         firstAuthor=true;
    //     }
    //     else{
    //         firstAuthor=false;
    //     }
    //     changePage();
    //     rank();
    // });

    $("#startYear").change(function(){
        startYear=$(this).val();
        changePage();
        rank();
    });

    $("#endYear").change(function(){
        endYear=$(this).val();
        changePage();
        rank();
    });

    $(".category").hover(function(){
        this.style.cursor='pointer';
    });

    $(".category").click(function(){
        $(".category").each(function(){
            $(this).css("background-color","white");
        });
        $(this).css("background-color","darkgrey");
        var text=$(this).text();
        if(text=="科研机构"){
            isAffiliation=true;
        }
        else{
            isAffiliation=false;
        }
        changePage();
        rank();
    })

    $("#confirmField").click(function(){
        field=$("#selectField").val();
        changePage();
        rank();
    })
    $("#selectField").bind('keypress', function (event) {
        if (event.keyCode == "13") {
            field=$("#selectField").val();
            changePage();
            rank();

        }
    })

    $(".amount").hover(function(){
        this.style.cursor='pointer';
    })

    $(".amount").click(function(){
        $(".amount").each(function(){
            $(this).css("color","black");
        })
        $(this).css("color","blue");
        var text=$(this).text();
        if(text=="论文数"){
            attribute="papers";
        }
        else if(text=="引用数"){
            attribute="refs";
        }
        changePage();
        rank();
    })

    $("#ranking").on('click','.item',function(){
        if(isAffiliation) {
            var text = $(this).find('td:first').text();
            var affiliation=text.substring(text.indexOf('.')+2);
            //alert(affiliation);
            $.ajax({
                type: 'POST',
                url: '/affiliation',
                async: true,
                data: JSON.stringify(affiliation),
                contentType: 'application/json',
                processData: false,
                success: function (res) {
                    localStorage.setItem('name', res.name);
                    localStorage.setItem('authors', res.authors);
                    localStorage.setItem('papers', res.papers);
                    localStorage.setItem('citations', res.citations);
                    localStorage.setItem('authorName', JSON.stringify(res.authorName));
                    window.location.href = "affiliationDetail";
                },
                error: function (error) {
                    alert("failure" + error);
                }
            })
        }
        else{
            var text = $(this).find('td:first').text();
            var author=text.substring(text.indexOf('.')+2);
            var affiliation=$(this).find('td:first').attr('data-affiliation');
            var map={};
            map['author']=author;
            map['affiliation']=affiliation;
            $.ajax({
                type: 'POST',
                url: '/authorPic/byName',
                async: true,
                data: JSON.stringify(map),
                contentType: 'application/json',
                processData: false,
                success: function (res) {
                    localStorage.setItem('author', res.author);
                    localStorage.setItem('affiliation', res.affiliation);
                    localStorage.setItem('authorPapers', res.papers);
                    localStorage.setItem('refs', res.refs);
                    window.location.href = "authorDetail";
                },
                error: function (error) {
                    alert("failure" + error);
                }
            })
        }

    })

    dTree.prototype.checkNode = function (id, pid, _hc, checked) {
        ////1、递归选父节点对象（无论是叶节点还是中间节点）
        ////判断同级中有无被选中的，如果有选中的就不可以反选
        if (!this.isHaveBNode(id, pid)) {
            if (checked) {
                //选中就一直选到根节点
                this.checkPNodeRecursion(pid, checked);
            } else {
                //        //去掉选中仅将其父节点去掉选中
                this.checkPNode(pid, checked);
            }
        }

        ////2、如果是中间结点，具有儿子，递归选子节点对象
        if (_hc)
            this.checkSNodeRecursion(id, checked);
        var count = 0;
        var obj = document.all.conference;
        conference="";

        for (i = 0; i < obj.length; i++) {
            if (obj[i].checked) {
                count++;
                if(obj[i].getAttribute("cshow")!=="All"){
                    conference+=obj[i].getAttribute("cshow")+";";
                }
            }
        }
        if(count==3)//根据具体会议数进行修改
            conference="All";
        count=0;
        changePage();
        rank();
        //alert(field+' '+conference);
    }

})

