$(document).ready(function () {
    var paperList;
    var authorList;
    var affiliationList;
    var currentPage=1;
    var pageNum;

    $('#nextPage').click(function(){
        if(currentPage==pageNum){
            alert("已经是最后一页了");
        }
        else {
            if(currentPage==1){
                document.getElementById("firstPage").setAttribute("class","");
                document.getElementById("prePage").setAttribute("class","");
            }
            if(currentPage==pageNum-1){
                document.getElementById("nextPage").setAttribute("class","hide");
                document.getElementById("lastPage").setAttribute("class","hide");
            }
            currentPage++;
            var searchText = $("#write").val();
            if (searchText !== '' && !searchText.match(/^[ ]*$/)) {
                getKeyWords(searchText);
            } else {
                alert("搜索内容不能为空");
            }
            localStorage.setItem('searchText', searchText);
        }
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
        var searchText = $("#write").val();
        if(searchText!==''&&!searchText.match(/^[ ]*$/)) {
            getKeyWords(searchText);
        }
        else{
            alert("搜索内容不能为空");
        }
        localStorage.setItem('searchText',searchText);
    })

    $("#firstPage").click(function(){
        currentPage=1;
        document.getElementById("firstPage").setAttribute("class","hide");
        document.getElementById("prePage").setAttribute("class","hide");
        document.getElementById("nextPage").setAttribute("class","");
        document.getElementById("lastPage").setAttribute("class","");
        var searchText = $("#write").val();
        if(searchText!==''&&!searchText.match(/^[ ]*$/)) {
            getKeyWords(searchText);
        }
        else{
            alert("搜索内容不能为空");
        }
        localStorage.setItem('searchText',searchText);
    })

    $("#lastPage").click(function(){
        currentPage=pageNum;
        document.getElementById("firstPage").setAttribute("class","");
        document.getElementById("prePage").setAttribute("class","");
        document.getElementById("nextPage").setAttribute("class","hide");
        document.getElementById("lastPage").setAttribute("class","hide");
        var searchText = $("#write").val();
        if(searchText!==''&&!searchText.match(/^[ ]*$/)) {
            getKeyWords(searchText);
        }
        else{
            alert("搜索内容不能为空");
        }
        localStorage.setItem('searchText',searchText);
    })

    function changePage(){
        currentPage=1;
        pageNum=0;
        document.getElementById("firstPage").setAttribute("class","hide");
        document.getElementById("prePage").setAttribute("class","hide");
        document.getElementById("nextPage").setAttribute("class","");
        document.getElementById("lastPage").setAttribute("class","");
    }






    if(localStorage.getItem('searchText')!='') {
        $("#write").val(localStorage.getItem('searchText'));
    }
    function getKeyWords(searchText) {

        var paperForm = searchText;
        $.ajax({
            type: 'POST',
            url: '/index',
            async: true,
            data: JSON.stringify(paperForm),
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                view(res);
                paperList=res;
            },
            error: function (error) {
                alert("failure" + error);
            }
        })
    }
    function view(list) {
        if(list.length==0){
            var text="<tr>"+"对不起，没有符合检索要求的论文，请重新输入关键字"+"</tr>";
            document.getElementById('one').innerHTML=text;
            //alert("没有符合检索要求的论文");
        }
        else{
            var text="";
            pageNum=Math.ceil(list.length/10);
            var len=0;//表示该作者在所有作者序列中是第几个
            for(var i=currentPage*10-10;i<currentPage*10;i++){
                if(i>=list.length){
                    break;
                }
                text =text+ "<div class='information'>"+
                    "<ul>"+
                    "<li class='title'>" + list[i].title + "</li>";

                var authors=list[i].authors.split(";");
                authorList=authors;
                text=text+"<li>"+"<a class='author' "+"id='author-"+(len)+"' href='#'>"+authors[0]+"</a>"+";";
                for(var j=1;j<authors.length-1;j++){
                    text=text+" "+"<a class='author' "+"id='author-"+(len+j)+"' href='#'>"+authors[j]+"</a>"+";";
                }
                text=text+" "+"<a class='author' "+"id='author-"+(len+j)+"' href='#'>"+authors[authors.length-1]+"</a>";

                text=text+"</li><li class='conference'>" + "<a href='#'>"+list[i].publicTitle + "</a>"+"</li>";

                var affiliations=list[i].affiliations.split("; ");
                affiliationList=affiliations;
                text=text+"<li style='display: none;'>"+"<a class='affiliation' "+"id='affiliation-"+(len)+"' href='#'>"+affiliations[0]+"</a>"+";";
                for(var j=1;j<affiliations.length-1;j++){
                    text=text+" "+"<a class='affiliation' "+"id='affiliation-"+(len+j)+"' href='#'>"+affiliations[j]+"</a>"+";";
                }
                text=text+" "+"<a class='affiliation' "+"id='affiliation-"+(len+j)+"' href='#'>"+affiliations[affiliations.length-1]+"</a>";

                len+=authors.length;

                var fields;
                if(list[i].authorKeyWord.search(";")!==-1)
                    fields=list[i].authorKeyWord.split(";");
                if(list[i].authorKeyWord.search(", ")!==-1)
                    fields=list[i].authorKeyWord.split(", ");
                else if(list[i].authorKeyWord.search(",")!==-1)
                    fields=list[i].authorKeyWord.split(",");
                text=text+"</li><li>"+"KeyWord:"+"<a class='field' href='#'>"+fields[0]+"</a>"+";";
                for(var j=1;j<fields.length-1;j++){
                    text=text+"  "+"<a class='field' href='#'>"+fields[j]+"</a>"+";";
                }
                text=text+"  "+"<a class='field' href='#'>"+fields[fields.length-1]+"</a>";

                    text=text+"</li><li>"+"Year:"+list[i].year+"</li>"+
                    "<li class='detail'>"+"<a href='#'>"+"Detail"+"</a>"+"</li>"+
                    "</ul>"+
                    "</div>";
                text=text+"<HR align=center width=800 color=#987cb9 SIZE=1>";
                //$('#one').append(text);
            }
            //alert(text);
            //$('#one').innerHTML = text;
            document.getElementById('one').innerHTML=text;//搜索新标签的时候应该重新显示，而不是在原来结果的后面append
        }
    }

    if(localStorage.getItem('searchText')!='') {
        var searchText = localStorage.getItem('searchText');
        getKeyWords(searchText);
    }

    $("#one").on('click','.field',function(){
        var keyword=$(this).text();
        $.ajax({
            type: 'POST',
            url: '/researchPortray',
            async: true,
            data: JSON.stringify(keyword),
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                localStorage.setItem('authorKeyWord',res.keyword);
                localStorage.setItem('author_count',res.author_count);
                localStorage.setItem('paper_count',res.paper_count);
                localStorage.setItem('reference_count',res.reference_count);

                window.location.href="researchField";
            },
            error: function (error) {
                alert("failure" + error);
            }
        })
    })

    $("#one").on('click','.author',function(){
        var author=$(this).text();
        if (author.charAt(0)==' '){
            author=author.substring(1,author.length);
        }
        var authorId=$(this).attr('id');
        var affiliationId=authorId.replace("author","affiliation");
        var affiliation=$("#"+affiliationId).text();

        $.ajax({
            type:'POST',
            url:'/authorPic/byName',
            async:false,
            data:JSON.stringify({
                'author':author,
                'affiliation':affiliation
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
    })

    $('#one').on('click','.conference',function () {
        var TopConferenceList = "";
        var title = $(this).text();
        $.ajax({
            type:'POST',
            url:'/conferPic',
            async:false,
            contentType: 'application/json',
            processData: false,
            success:function (res) {
                TopConferenceList = res;
            },
            error:function (error) {
                alert(error);
            }
        });
        for(var i =0;i<TopConferenceList.length;i++){
            if(TopConferenceList[i].name == title){
                localStorage.setItem('Id',TopConferenceList[i].id);
                localStorage.setItem('title',title);
                localStorage.setItem('paperCount',TopConferenceList[i].paperCount);
                localStorage.setItem('referCount',TopConferenceList[i].referCount);
            }
        }
        window.location.href = "conferPicPage";
    });

    $("#one").on('click','.affiliation',function(){
        var affiliation=$(this).text();
        $.ajax({
            type: 'POST',
            url: '/affiliation',
            async: true,
            data: JSON.stringify(affiliation),
            contentType: 'application/json',
            processData: false,
            success: function (res) {
                localStorage.setItem('name',res.name);
                localStorage.setItem('authors',res.authors);
                localStorage.setItem('papers',res.papers);
                localStorage.setItem('citations',res.citations);
                localStorage.setItem('authorName',JSON.stringify(res.authorName));
                window.location.href="affiliationDetail";
            },
            error: function (error) {
                alert("failure" + error);
            }
        })

    })

    $("#one").on('click','.detail',function(){
        var title=$(this).prev().prev().prev().prev().prev().prev().text();
        for(var i=0;i<paperList.length;i++){
            if(paperList[i].title==title){
                localStorage.setItem('title',paperList[i].title);
                localStorage.setItem('authors',paperList[i].authors);
                localStorage.setItem('affiliations',paperList[i].affiliations);
                localStorage.setItem('publicTitle',paperList[i].publicTitle);
                localStorage.setItem('year',paperList[i].year);
                localStorage.setItem('startPage',paperList[i].startPage);
                localStorage.setItem('endPage',paperList[i].endPage);
                localStorage.setItem('pdfLink',paperList[i].pdfLink);
                localStorage.setItem('authorKeyWord',paperList[i].authorKeyWord);
                localStorage.setItem('referenceCount',paperList[i].referenceCount);
                localStorage.setItem('publisher',paperList[i].publisher);
                localStorage.setItem('docID',paperList[i].docID);
                localStorage.setItem('paperAbstract',paperList[i].paperAbstract);
                break;
            }
        }
        window.location.href="detail";
    })

    $("#search").click(function () {
        var searchText = $("#write").val();
        if(searchText!==''&&!searchText.match(/^[ ]*$/)) {
            getKeyWords(searchText);
        }
        else{
            alert("搜索内容不能为空");
        }
        localStorage.setItem('searchText',searchText);
    })
    $("#write").bind('keypress', function (event) {
        if (event.keyCode == "13") {
            var searchText = $("#write").val();
            if(searchText!==''&&!searchText.match(/^[ ]*$/)) {
                getKeyWords(searchText);
            }
            else{
                alert("搜索内容不能为空");
            }
            localStorage.setItem('searchText',searchText);

        }
    })

    $("#get-back").click(
        function(){
            window.location.href="/";
        }
    )

    var pageParam = {
        next: '.next',//下一页按钮jq选择器
        prev: '.prev',//上一页按钮jq选择器
        nextMore: '.nextMore',//下n页按钮jq选择器
        prevMore: '.prevMore',//上n页按钮jq选择器
        totalEl: '.total',//总页数jq元素  元素内包含 eq:“共n页”
        curPageEl: '.cur_page',//当前页数jq元素  元素内包含 eq:“当前第n页”
        perPageCount: 4,//每页显示数量
        morePage: 5//上、下n页跳转数
    }
    //demo为包裹列表的容器
    $("#one").page(pageParam);
})