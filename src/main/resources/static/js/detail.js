$(document).ready(function () {
    var title="Title: "+localStorage.getItem("title");
    var authors="Authors: "+localStorage.getItem("authors");
    var affiliations="Affiliations: "+localStorage.getItem("affiliations");
    var publicTitle="PublicTitle: "+localStorage.getItem("publicTitle");
    var year="Year: "+localStorage.getItem("year");
    var startPage="StartPage: "+localStorage.getItem("startPage");
    var endPage="EndPage: "+localStorage.getItem("endPage");
    var pdfLink="PdfLink: "+localStorage.getItem("pdfLink");
    var authorKeyWord="AuthorKeyWord: "+localStorage.getItem("authorKeyWord");
    var referenceCount="ReferenceCount: "+localStorage.getItem("referenceCount");
    var publisher="Publisher: "+localStorage.getItem("publisher");
    var docID="DocID: "+localStorage.getItem("docID");
    var paperAbstract="PaperAbstract: "+localStorage.getItem("paperAbstract");
    $("#title").text(title);
    $("#affiliations").text(affiliations);
    $("#authors").text(authors);
    $("#publicTitle").text(publicTitle);
    $("#year").text(year);
    $("#startPage").text(startPage);
    $("#endPage").text(endPage);
    //$("#pdfLink").text(pdfLink);
    document.getElementById("pdfLink").innerHTML="PdfLink: "+"<a target='_blank' href='"+localStorage.getItem("pdfLink")+"'"+">"+localStorage.getItem("pdfLink")+"<a>";
    $("#authorKeyWord").text(authorKeyWord);
    $("#referenceCount").text(referenceCount);
    $("#publisher").text(publisher);
    $("#docID").text(docID);
    $("#paperAbstract").text(paperAbstract);
})