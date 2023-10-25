package com.example.cocktailcompass.cocktail.controllers;

import com.example.cocktailcompass.cocktail.dtos.CocktailDTO;
import com.example.cocktailcompass.cocktail.exceptions.FavouriteCocktailServiceException;
import com.example.cocktailcompass.cocktail.sevices.CocktailService;
import com.example.cocktailcompass.cocktail.models.FavouriteCocktail;

import com.example.cocktailcompass.cocktail.sevices.FavouriteCocktailServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cocktails/favourites")
public class FavouriteCocktailController {

    CocktailService cocktailService;
    FavouriteCocktailServiceImpl favouriteCocktailService;

    @Autowired
    public FavouriteCocktailController(CocktailService cocktailService, FavouriteCocktailServiceImpl favouriteCocktailService) {
        this.cocktailService = cocktailService;
        this.favouriteCocktailService = favouriteCocktailService;
    }

    @PostMapping
    public FavouriteCocktail saveFavouriteCocktail(@RequestBody FavouriteCocktail favouriteCocktail) throws FavouriteCocktailServiceException {
        ModelMapper modelMapper = new ModelMapper();
        CocktailDTO cocktailDTO = new ModelMapper().map(favouriteCocktail, CocktailDTO.class);

        CocktailDTO favouriteCocktailDTO = favouriteCocktailService.saveFavouriteCocktail(cocktailDTO);
        return modelMapper.map(favouriteCocktailDTO, FavouriteCocktail.class);
    }

    @GetMapping
    public ResponseEntity<List<FavouriteCocktail>> getAllFavouriteCocktails() {
        List<FavouriteCocktail> favouriteCocktails = cocktailService.getAllFavouriteCocktails();
        if (favouriteCocktails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(favouriteCocktails, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavouriteCocktail(@PathVariable Long id) {
        cocktailService.deleteFavouriteCocktail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
