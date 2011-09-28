package pl.softwaremill.demo.rewrite;
import javax.servlet.ServletContext;
import com.ocpsoft.rewrite.config.Configuration;
import com.ocpsoft.rewrite.config.ConfigurationBuilder;
import com.ocpsoft.rewrite.config.Direction;
import com.ocpsoft.rewrite.servlet.config.DispatchType;
import com.ocpsoft.rewrite.servlet.config.Forward;
import com.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import com.ocpsoft.rewrite.servlet.config.Path;
import com.ocpsoft.rewrite.servlet.config.rule.Join;
import com.ocpsoft.rewrite.servlet.config.rule.TrailingSlash;
/** 
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public class URLRewriteConfiguration extends HttpConfigurationProvider {
  private static final String ENTITY_NAME="[a-zA-Z$_0-9]+";
  @Override public Configuration getConfiguration(  ServletContext context){
    return ConfigurationBuilder.begin()
            .addRule(Join.path("/").to("/index.jsf"))
            .addRule(Join.path("/{domain}").where("domain").matches(ENTITY_NAME).to("/scaffold/{domain}/list.jsf").withInboundCorrection())
            .addRule(Join.path("/{domain}/{id}").where("domain").matches(ENTITY_NAME).where("id").matches("\\d+").to("/scaffold/{domain}/view.jsf").withInboundCorrection())
            .addRule(Join.path("/{domain}/history/{id}").where("domain").matches(ENTITY_NAME).where("id").matches("\\d+").to("/scaffold/{domain}/history.jsf").withInboundCorrection())
            .addRule(Join.path("/{domain}/create").where("domain").matches(ENTITY_NAME).to("/scaffold/{domain}/create.jsf").withInboundCorrection())
            .addRule(Join.path("/404").to("/404.jsf"))
            .addRule(Join.path("/error").to("/500.jsf"));
  }
  @Override public int priority(){
    return 1;
  }
}
