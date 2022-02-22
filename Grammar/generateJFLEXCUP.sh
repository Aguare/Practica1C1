echo "GENERATING LEXERS"
echo "------> deleting files on /Java/Back"
rm -rf ../java/Back/AnalizerPractice.java 
echo "------> compiling .jflex files"
jflex LexPractice.jflex
echo "------> moving new .jflex files to /Lexer/"
mv .java AnalizerPractice.java ../java/Back
echo "GENERATING PARSERS AND SYM TABLE" 
echo "------> deleting files on /Parser/"
rm -rf ../java/Back/SintacticParser.java ../java/Back/sym.java
echo "------> compiling .cup file"
cup -parser SintacticParser -symbols sym Sintactic.cup 
echo "------> moving new .java files to /Parser/"
mv SintacticParser.java sym.java ../java/Back
