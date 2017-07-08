package logogin.util;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;

import com.sun.facelets.FaceletContext;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.TagConfig;
import com.sun.facelets.tag.TagHandler;

/**
 * @created 2008-12-10
 * @author logogin
 */
public class SetELHandler extends TagHandler {

    private TagAttribute var;
    private TagAttribute value;
    
    public SetELHandler(TagConfig config) {
        super(config);
        var = getRequiredAttribute("var");
        value = getRequiredAttribute("value");
    }
    
    public void apply(FaceletContext ctx, UIComponent parent) {
        String varStr = var.getValue(ctx);
        ValueExpression veObj = value.getValueExpression(ctx, String.class);
        String expr = (String)veObj.getValue(ctx);
        veObj = ctx.getExpressionFactory().createValueExpression(ctx, expr, Object.class);
        ctx.getVariableMapper().setVariable(varStr, veObj);
    }
}
