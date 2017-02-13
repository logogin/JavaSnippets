package steuerrechner;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DefaultHandler2;

import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.sun.codemodel.JArray;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JConditional;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JDocCommentable;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;

/**
 * JavaGenerator.java
 *
 * @author Pavel Danchenko
 * @date Oct 27, 2014
 *
 */
public class JavaGenerator extends DefaultHandler2 {

    public static void main(String[] args) throws Exception {
        JavaGenerator generator = new JavaGenerator();

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", generator);
        saxParser.parse(new File("src/main/resources/Lohnsteuer2013_2.xml"), generator);
    }

    private JCodeModel cm;
    private JDefinedClass dc;
    private JDocCommentable cc;
    private JMethod method;
    private Deque<JBlock> blocks = new LinkedList<JBlock>();
    private Deque<JConditional> conds = new LinkedList<JConditional>();
    private Deque<String> comments = new LinkedList<String>();

    private String prev;
    private JConditional prevIf;
    private boolean elsif = false;

    @Override
    public void startDocument() throws SAXException {
        cm = new JCodeModel();
    }

    @Override
    public void endDocument() throws SAXException {
        try {
            assert blocks.size() == 0;
            assert conds.size() == 0;
            cm.build(new File("target/generated"));
        } catch (IOException ex) {
            Throwables.propagate(ex);
        }
    }

    @Override
    public void comment(char[] ch, int start, int length) throws SAXException {
        comments.add(String.valueOf(ch).trim().replaceAll(" +", " "));
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            JDocCommentable commentable = null;
            if ( "PAP".equals(qName) ) {
                dc = cm._class("steuerrechner." + attributes.getValue("name"));
            }
            if ( qName.equals("INPUT") ) {
                JFieldVar fv = field(JMod.PUBLIC, attributes);
                commentable = fv;
            }
            if ( qName.equals("OUTPUT") ) {
                JFieldVar fv = field(JMod.PUBLIC, attributes);
                commentable = fv;
            }

            if ( qName.equals("INTERNAL") ) {
                JFieldVar fv = field(JMod.PRIVATE, attributes);
                commentable = fv;
            }
            if ( qName.equals("CONSTANT") ) {
                JFieldVar fv = constant(attributes);
                commentable = fv;
            }
            if ( "MAIN".equals(qName) ) {
                method = dc.method(JMod.PUBLIC, JType.parse(cm, "void"), "main");
                blocks.push(method.body());
                commentable = method;
            }
            if ( "METHOD".equals(qName) ) {
                method = dc.method(JMod.PUBLIC, JType.parse(cm, "void"), attributes.getValue("name"));
                blocks.push(method.body());
                commentable = method;
            }

            if ( "EXECUTE".equals(qName) ) {
                blocks.peek().invoke(attributes.getValue("method"));
            }
            if ( "IF".equals(qName) ) {
//                if ( elsif ) {
//                    System.out.println(indent(conds.size()) + "start elsif " + attributes.getValue("expr"));
//                    conds.push(prevIf._elseif(JExpr.direct(attributes.getValue("expr"))));
//                    elsif = false;
//                } else {
                    System.out.println(indent(conds.size()) + "start if " + attributes.getValue("expr"));
                    conds.push(blocks.peek()._if(JExpr.direct(attributes.getValue("expr"))));
//                }
            }
            if ( "THEN".equals(qName) ) {
                blocks.push(conds.peek()._then());
            }
            if ( "ELSE".equals(qName) ) {
                if (prev == "if") {
                    blocks.push(prevIf._else());
                    prevIf = null;
                } else {
                    blocks.push(conds.peek()._else());
                }
            }
            if ( "EVAL".equals(qName) ) {
                blocks.peek().directStatement(attributes.getValue("exec") + ";");
            }

            if ( null != commentable && !comments.isEmpty() ) {
                commentable.javadoc().append(Joiner.on('\n').join(comments));
                comments.clear();
            }

        } catch (Exception ex) {
            Throwables.propagate(ex);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ( "MAIN".equals(qName) ) {
            blocks.pop();
        }
        if ( "METHOD".equals(qName) ) {
            blocks.pop();
        }
        if ( "IF".equals(qName) ) {
            prev = "if";
            prevIf = conds.pop();
            //conds.pop();
            System.out.println(indent(conds.size()) + "end if");
        }
        if ( "THEN".equals(qName) ) {
            prev = "then";
            blocks.pop();
        }
        if ( "ELSE".equals(qName) ) {
            //assert prev == "then";
            prev = "else";
            blocks.pop();
        }
    }

    private String indent(int count) {
        String indent = "";
        for ( int i=0; i<count; i++ ) {
            indent += " ";
        }
        return indent;
    }

    private JFieldVar field(int mods, Attributes attributes) {
        JFieldVar fv = dc.field(mods, getType(attributes.getValue("type")), attributes.getValue("name"));
        if ( null != attributes.getValue("default") ) {
            if ( fv.type().name().equals("int") ) {
                JExpression expr = JExpr.lit((int)Double.parseDouble(attributes.getValue("default")));
                fv.init(expr);
            } else {
                fv.init(JExpr.direct(attributes.getValue("default")));
            }
        }
        return fv;
    }

    private JFieldVar constant(Attributes attributes) {
        JFieldVar fv = dc.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, getType(attributes.getValue("type")), attributes.getValue("name"));
        if ( null != attributes.getValue("value") ) {
            if (fv.type().isArray()) {
                String[] values = attributes.getValue("value").replaceAll("\\{", "").replaceAll("\\}", "").split(",");
                JArray arr = JExpr.newArray(cm.ref(BigDecimal.class));
                for (String value : values) {
                    arr.add(JExpr.direct(value.trim()));
                }
                fv.init(arr);
            } else {
                fv.init(JExpr.direct(attributes.getValue("value")));
            }
        }
        return fv;
    }

    public JType getType(String typeName) {
        try {
            return JType.parse(cm, typeName);
        } catch (IllegalArgumentException ex) {
            if ( "BigDecimal".equals(typeName) ) {
                return cm.ref(BigDecimal.class);
            }
            if ( "BigDecimal[]".equals(typeName) ) {
                return cm.ref(BigDecimal[].class);
            }
            throw ex;
        }
    }
}
