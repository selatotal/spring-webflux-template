package br.com.selat.template;

import br.com.selat.template.contract.v1.PersonService;
import br.com.selat.template.person.v1.PersonServiceImpl;

public class TemplateServiceDriver {

    private TemplateServiceDriver(){}

    public static PersonService buildPersonService(){
        return new PersonServiceImpl();
    };
}
