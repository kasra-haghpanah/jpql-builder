package com.jpql.api.interfaces;

/**
 * Created by kasra.haghpanah on 10/15/2018.
 */
public interface DTO<D, M extends Model> {

    public M convertToJPAModel();

    public D convertToDTO(M jpaModel);

}
