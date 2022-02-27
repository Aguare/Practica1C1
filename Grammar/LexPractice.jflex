/* SECTION 1: user code */
package Back;
import java_cup.runtime.Symbol;
import Back.Control.ErrorDesc;
import java.util.ArrayList;

/* SECTION 2: config */

%%
%class AnalizerPractice
%unicode
%line
%column
%cup
%public

//arithmetic operators
SUM = "+"
RES = "-"
MUL = "*"
DIV = "/"

//Comments
COM = ("#")(.)*

//Graphics values
SG = Def|def
TB = "Barras"
TPIE = "Pie"
IDG = "titulo"
AX = "ejex"
AY = "ejey"
TAG = "etiquetas"
VAL = "valores"
LK = "unir"
TP = "tipo"
T_ATTRIBUTE = Cantidad|Porcentaje
TT = "total"
EX = "extra"
RUN = "Ejecutar"

//Other Symbols
O_BRACE = "{"
C_BRACE = "}"
O_SBRACKET = "["
C_SBRACKET = "]"
O_PARENT = "("
C_PARENT = ")"
SEMICOLON = ";"
COLONS = ":"
STRING = \"([a-zA-Z]|(\s)|[0-9])+\"
COMMA = ","
INTEGER = [0-9]+
DECIMAL = \d+(\.\d+)?
W = [\s\t\r\f\n]+

%{
    ArrayList<ErrorDesc> errors = new ArrayList<>();

    public void addError(String value, int line, int column){
        errors.add(new ErrorDesc(value, line, column,
         "Símbolo no existe en el lenguaje", "Léxico"));
    }

    public void showErrors() {
        for (ErrorDesc error : errors) {
            System.out.println("Lexema:" + error.getContent() + " L:" + error.getLine()
            + " C:" + error.getColumn() + " Tipo:" + error.getTypeError() + " " + error.getMsjInfo());
        }
    }
%}

%%

/* SECTION 3: lexical rules */
{SG}            {return new Symbol(sym.START_GRAPHIC, yyline+1, yycolumn+1, yytext());}
{TB}            {return new Symbol(sym.TYPE_BARS, yyline+1, yycolumn+1, yytext());}
{TPIE}          {return new Symbol(sym.TYPE_PIE, yyline+1, yycolumn+1, yytext());}
{IDG}           {return new Symbol(sym.ID_GRAPHIC, yyline+1, yycolumn+1, yytext());}
{AX}            {return new Symbol(sym.AXIS_X, yyline+1, yycolumn+1, yytext());}
{AY}            {return new Symbol(sym.AXIS_Y, yyline+1, yycolumn+1, yytext());}
{TAG}           {return new Symbol(sym.TAGS, yyline+1, yycolumn+1, yytext());}
{VAL}           {return new Symbol(sym.VALUES, yyline+1, yycolumn+1, yytext());}
{LK}            {return new Symbol(sym.JOIN, yyline+1, yycolumn+1, yytext());}
{TP}            {return new Symbol(sym.TYPE, yyline+1, yycolumn+1, yytext());}
{T_ATTRIBUTE}   {return new Symbol(sym.TYPE_ATTRIBUTE, yyline+1, yycolumn+1, yytext());}
{TT}            {return new Symbol(sym.TOTAL, yyline+1, yycolumn+1, yytext());}
{EX}            {return new Symbol(sym.EXTRA, yyline+1, yycolumn+1, yytext());}
{RUN}           {return new Symbol(sym.RUN, yyline+1, yycolumn+1, yytext());}
{SUM}           {return new Symbol(sym.SUMA, yyline+1, yycolumn+1, yytext());}
{RES}           {return new Symbol(sym.RESTA, yyline+1, yycolumn+1, yytext());}
{MUL}           {return new Symbol(sym.MULTIPLY, yyline+1, yycolumn+1, yytext());}
{DIV}           {return new Symbol(sym.DIVIDE, yyline+1, yycolumn+1, yytext());}
{O_BRACE}       {return new Symbol(sym.O_BRACE, yyline+1, yycolumn+1, yytext());}
{C_BRACE}       {return new Symbol(sym.C_BRACE, yyline+1, yycolumn+1, yytext());}
{O_SBRACKET}    {return new Symbol(sym.O_SBRACKET, yyline+1, yycolumn+1, yytext());}
{C_SBRACKET}    {return new Symbol(sym.C_SBRACKET, yyline+1, yycolumn+1, yytext());}
{O_PARENT}      {return new Symbol(sym.O_PARENT, yyline+1, yycolumn+1, yytext());}
{C_PARENT}      {return new Symbol(sym.C_PARENT, yyline+1, yycolumn+1, yytext());}
{SEMICOLON}     {return new Symbol(sym.SEMICOLON, yyline+1, yycolumn+1, yytext());}
{COLONS}        {return new Symbol(sym.COLONS, yyline+1, yycolumn+1, yytext());}
{STRING}        {return new Symbol(sym.STRING, yyline+1, yycolumn+1, yytext());}
{COMMA}         {return new Symbol(sym.COMMA, yyline+1, yycolumn+1, yytext());}
{INTEGER}       {return new Symbol(sym.INTEGER, yyline+1, yycolumn+1, new Integer(yytext()));}
{DECIMAL}       {return new Symbol(sym.DECIMAL, yyline+1, yycolumn+1, new Double(yytext()));}
{W}             {}
{COM}           {}
[^]             {addError(yytext(), yyline+1, yycolumn+1);}
