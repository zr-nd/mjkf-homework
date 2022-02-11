$(document).ready(function () {
    var title="Title: "+localStorage.getItem("dtitle");
    var authors="Authors: "+localStorage.getItem("dauthors");
    var affiliations="Affiliations: "+localStorage.getItem("daffiliations");
    var publicTitle="PublicTitle: "+localStorage.getItem("dpublicTitle");
    var year="Year: "+localStorage.getItem("dyear");
    var startPage="StartPage: "+localStorage.getItem("dstartPage");
    var endPage="EndPage: "+localStorage.getItem("dendPage");
    var pdfLink="PdfLink: "+localStorage.getItem("dpdfLink");
    var authorKeyWord="AuthorKeyWord: "+localStorage.getItem("dauthorKeyWord");
    var referenceCount="ReferenceCount: "+localStorage.getItem("dreferenceCount");
    var publisher="Publisher: "+localStorage.getItem("dpublisher");
    var docID="DocID: "+localStorage.getItem("ddocID");
    var paperAbstract="PaperAbstract: "+localStorage.getItem("dpaperAbstract");
    $("#title").text(title);
    $("#affiliations").text(affiliations);
    $("#authors").text(authors);
    $("#publicTitle").text(publicTitle);
    $("#year").text(year);
    $("#startPage").text(startPage);
    $("#endPage").text(endPage);
    //$("#pdfLink").text(pdfLink);
    document.getElementById("pdfLink").innerHTML="PdfLink: "+"<a target='_blank' href='"+localStorage.getItem("dpdfLink")+"'"+">"+localStorage.getItem("dpdfLink")+"<a>";
    $("#authorKeyWord").text(authorKeyWord);
    $("#referenceCount").text(referenceCount);
    $("#publisher").text(publisher);
    $("#docID").text(docID);
    $("#paperAbstract").text(paperAbstract);
})