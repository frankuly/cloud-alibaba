package ribbonconfiguration;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule() {
       return new NacosRule();
    }

}
