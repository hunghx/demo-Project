package ra.model.services.catalog;

import ra.model.entity.Catalog;
import ra.model.services.IServices;

import java.util.List;

public interface ICatalogService extends IServices<Catalog,String> {
    List<Catalog> findByName(String name);
}
