<?php
require_once("Java.inc");
java_require("sql_analysis1.jar");

$sql_analysis=new Java("sql_analysis");

if (!isset($_GET['text'])||empty($_GET['text'])){
    echo "<script>alert('请输入命令');window.location.href='index.php'</script>";
}

$text_arr=explode(' ',$_GET['text']);

if ($text_arr[0]=='help'){
    header('location:help.php');
}elseif ($text_arr[0]=='select'){
    if ($sql_analysis->execute($_GET['text'])){
        header('location:selectResult.php');
    }else{
        echo "<script>alert('查询失败');window.location.href='index.php'</script>";
    }
}elseif ($text_arr[0]=='insert'){
    if ($sql_analysis->execute($_GET['text'])){
        header('location:insertResult.php');
    }else{
        echo "<script>alert('插入失败');window.location.href='index.php'</script>";
    }
}elseif ($text_arr[0]=='update'){
    if ($sql_analysis->execute($_GET['text'])){
        header('location:updateResult.php');
    }else{
        echo "<script>alert('更新失败');window.location.href='index.php'</script>";
    }
}elseif ($text_arr[0]=='delete'){
    if ($sql_analysis->execute($_GET['text'])){
        header('location:deleteResult.php');
    }else{
        echo "<script>alert('删除失败');window.location.href='index.php'</script>";
    }
}else{
    echo "<script>alert('命令错误');window.location.href='index.php'</script>";
}