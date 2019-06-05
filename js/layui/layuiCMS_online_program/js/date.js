/**
 * Created by ubuntu on 17-2-3.
 */
window.onload = function(){
    strYYYY = document.form1.year.outerHTML;
    strMM = document.form1.month.outerHTML;
    strDD = document.form1.day.outerHTML;
    MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

//先给年下拉框赋内容
    var y = new Date().getFullYear();
    var str = strYYYY.substring(0, strYYYY.length - 9);
    for (var i = (y-30); i < (y+30); i++) //以今年为准，前30年，后30年
    {
        str += "<option value='" + i + "'> " + i + "</option>\r\n";
    }
    document.form1.year.outerHTML = str +"</select>";

//赋月份的下拉框
    var str = strMM.substring(0, strMM.length - 9);
    for (var i = 1; i < 13; i++)
    {
        str += "<option value='" + i + "'> " + i + "</option>\r\n";
    }
    document.form1.month.outerHTML = str +"</select>";

    document.form1.year.value = y;
    document.form1.month.value = new Date().getMonth() + 1;
    var n = MonHead[new Date().getMonth()];
    if (new Date().getMonth() ==1 && IsPinYear(YYYYvalue)) n++;
    writeDay(n); //赋日期下拉框
    document.form1.day.value = new Date().getDate();
}
function yearMonth(str) //年发生变化时日期发生变化(主要是判断闰平年)
{
    var MMvalue = document.form1.month.options[document.form1.month.selectedIndex].value;
    if (MMvalue == ""){day.outerHTML = strDD; return;}
    var n = MonHead[MMvalue - 1];
    if (MMvalue ==2 && IsPinYear(str)) n++;
    writeDay(n)
}
function monthDay(str) //月发生变化时日期联动
{
    var YYYYvalue = document.form1.year.options[document.form1.year.selectedIndex].value;
    if (str == ""){day.outerHTML = strDD; return;}
    var n = MonHead[str - 1];
    if (str ==2 && IsPinYear(YYYYvalue)) n++;
    writeDay(n)
}  function writeDay(n) //据条件写日期的下拉框
{
    var s = strDD.substring(0, strDD.length - 9);
    for (var i=1; i<(n+1); i++)
        s += "<option value='" + i + "'> " + i + "</option>\r\n";
    document.form1.day.outerHTML = s +"</select>";
}
function IsPinYear(year)//判断是否闰平年
{ return(0 == year%4 && (year%100 !=0 || year%400 == 0))}
