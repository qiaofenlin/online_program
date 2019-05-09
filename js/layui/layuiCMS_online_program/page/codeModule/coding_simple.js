layui.config({
    base : "js/"
}).use(['form','layer','jquery','laypage','element'],function(){
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,element = layui.element,
        $ = layui.jquery;
    window.simple_coding=CodeMirror.fromTextArea(document.getElementById("code_test"), {
        mode: {name: "text/x-cython",
            version: 2,
            singleLineStringErrors: false},
        lineNumbers: true,
        indentUnit: 4,
        matchBrackets: true,
    });


// output functions are configurable.  This one just appends some text
    // to a pre element.
    //导入json
    Sk.externalLibraries = {
        json : {
            path : 'json.sk/__init__.js',
            dependencies : [
                'json.sk/stringify.js'
            ]
        }
    };
    function outf(text) {
        var mypre = document.getElementById("output");
        mypre.innerHTML = mypre.innerHTML + text;
        console.log("************mypre.innerHTML + text "+ mypre.innerHTML + text)
    }
    function builtinRead(x) {
        if (Sk.builtinFiles === undefined || Sk.builtinFiles["files"][x] === undefined)
            throw "File not found: '" + x + "'";
        console.log("*******************Sk.builtinFiles[\"files\"][x]"+Sk.builtinFiles["files"][x])
        return Sk.builtinFiles["files"][x];
    }
    // Here's everything you need to run a python program in skulpt
    // grab the code from your textarea
    // get a reference to your pre element for output
    // configure the output function
    // call Sk.importMainWithBody()
    function runit() {
        var prog = simple_coding.getTextArea().value;
        console.log("************code"+prog)
        var mypre = document.getElementById("output");
        console.log("************mypre "+mypre)

        mypre.innerHTML = '';
        Sk.pre = "output";
        // console.log("-----------------1")
        Sk.configure({output:outf, read:builtinRead});
        // console.log("-----------------2" +Sk.TurtleGraphics)
        // console.log("-----------------3" +Sk.TurtleGraphics)

        (Sk.TurtleGraphics || (Sk.TurtleGraphics = {})).target = 'mycanvas';
        var myPromise = Sk.misceval.asyncToPromise(function() {
            return Sk.importMainWithBody("<stdin>", false, prog, true);
        });
        myPromise.then(function(mod) {
                console.log('success');
            },
            function(err) {
                console.log(err.toString());
            });
    }

    $("body").on("click",".run_simple_it",function(){  //编辑
        runit()
    })


})