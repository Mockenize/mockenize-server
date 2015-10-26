/**
 * Created by rodrigo on 10/25/15.
 */
var mockenize = angular.module('mockenize', ['ui.bootstrap', 'ui.codemirror']);

mockenize.constant("codeMirrorOptions", {
    lineNumbers: true,
    smartIndent: true,
    indentWithTabs: true,
    lineWrapping: true,
    mode: "javascript",
    onLoad : function(_cm){
        _cm.refresh();
    }
});