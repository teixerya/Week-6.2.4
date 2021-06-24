package sheridan.teixerya.service;




import org.springframework.stereotype.Service;
import sheridan.teixerya.model.CustomerForm;
import sheridan.teixerya.repository.CustomerDataRepository;
import sheridan.teixerya.repository.CustomerEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerDataServiceImpl implements CustomerDataService {

    private final CustomerDataRepository customerDataRepository;

    CustomerDataServiceImpl(CustomerDataRepository customerDataRepository){
        this.customerDataRepository = customerDataRepository;
    }

    private static void copyFormToEntity(CustomerForm form, CustomerEntity country){

        country.setName(form.getName());
        country.setContinent(form.getContinent());
        country.setRegion(form.getRegion());
        country.setSurfaceArea(form.getSurfaceArea());
        country.setPopulation(form.getPopulation());

    }

    private static void copyEntityToForm(CustomerEntity country, CustomerForm form){
        form.setId(country.getId());
        form.setName(country.getName());
        form.setContinent(country.getContinent());
        form.setRegion(country.getRegion());
        form.setSurfaceArea(country.getSurfaceArea());
        form.setPopulation(country.getPopulation());

    }



    public List<CustomerForm> getAllCustomerForms() {
        List<CustomerForm> formList = new ArrayList<>();
        List<CustomerEntity> customerList = customerDataRepository.findAll();
        for(CustomerEntity country: customerList){
            CustomerForm form = new CustomerForm();
            copyEntityToForm(country, form);
            formList.add(form);
        }
        return formList;
    }



    public CustomerForm getCustomerForm(int id) {
        Optional<CustomerEntity> result = customerDataRepository.findById(id);
        if(result.isPresent()){
            CustomerForm form = new CustomerForm();
            CustomerEntity country = result.get();
            copyEntityToForm(country, form);
            return form;
        }
        return null;
    }



}



