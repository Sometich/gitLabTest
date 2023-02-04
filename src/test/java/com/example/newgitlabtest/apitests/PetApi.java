package com.example.newgitlabtest.apitests;

import com.example.newgitlabtest.pojo.DeleteResponse;
import com.example.newgitlabtest.pojo.Pet;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;


public interface PetApi {
    @RequestLine("POST /pet")
    @Headers("Content-type:application/json")
    Pet addPet(Pet pet);

    @RequestLine("PUT /pet")
    @Headers("Content-type:application/json")
    Pet updatePet(Pet pet);

    @RequestLine("GET /pet/{petId}")
    @Headers("Content-type:application/json")
    Pet getPet(@Param("petId") long petId);

    @RequestLine("DELETE /pet/{petId}")
    @Headers({"Content-type: application/json", "api_key: special-key"})
    DeleteResponse deletePet(@Param("petId") long petId);

    @RequestLine("DELETE /pet/{petId}")
    @Headers({"Content-type: application/json", "api_key: special-key"})
    Response getDeleteStatusCodePet(@Param("petId") long petId);

    @RequestLine("POST /pet")
    @Headers("Content-type:application/json")
    Response getAddStatusCodePet(Pet pet);

    @RequestLine("GET /pet/{petId}")
    @Headers("Content-type:application/json")
    Response getStatusCodePet(@Param("petId") long petId);

    @RequestLine("PUT /pet")
    @Headers("Content-type:application/json")
    Response getStatusUpdatePet(Pet pet);
}
