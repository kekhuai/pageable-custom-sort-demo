package com.example.pageablecustomsortdemo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

@Configuration
public class CustomSortConfig extends SpringDataWebConfiguration {
    
    private final ApplicationContext applicationContext;

    @Autowired
    public CustomSortConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    @Bean
    public SortHandlerMethodArgumentResolver sortResolver() {
        SortHandlerMethodArgumentResolver resolver = new CustomSortHandlerMethodArgumentResolver();
        return resolver;
    }
    
    private class CustomSortHandlerMethodArgumentResolver extends SortHandlerMethodArgumentResolver {

        @Override
        public Sort resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
            Sort oldSort = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
            List<Order> orders = new ArrayList<>();
            for (Order order : oldSort) {
                orders.add(order.ignoreCase());
            }
            return new Sort(orders);
//            String[] directionParameter = webRequest.getParameterValues(getSortParameter(parameter));
//
//            // No parameter
//            if (directionParameter == null) {
//                return null;
//            }
//
//            // Single empty parameter, e.g "sort="
//            if (directionParameter.length == 1 && !StringUtils.hasText(directionParameter[0])) {
//                return null;
//            }
//
//            return parseParameterIntoSort(directionParameter, ",");
        }
        
        private Sort parseParameterIntoSort(final String[] source, final String delimiter) {

            List<Order> allOrders = new ArrayList<Sort.Order>();

            for (String part : source) {

                if (part == null) {
                    continue;
                }

                String[] elements = part.split(delimiter);
                Direction direction = elements.length == 0 ? null : Direction.fromStringOrNull(elements[elements.length - 1]);
                Order order = null;

                for (int i = 0; i < elements.length; i++) {

                    if (i == elements.length - 1 && direction != null) {
                        continue;
                    }

                    String property = elements[i];

                    if (!StringUtils.hasText(property)) {
                        continue;
                    }
                    
                    if ("ignoreCase".equals(property)) {
                        order = order.ignoreCase();
                    } else {
                        order = new Order(direction, property);
                    }
                }
                allOrders.add(order);
            }

            return allOrders.isEmpty() ? null : new Sort(allOrders);
        }
        
    }

}
