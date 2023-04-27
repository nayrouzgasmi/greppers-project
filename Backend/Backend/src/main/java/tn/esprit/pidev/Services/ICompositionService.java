package tn.esprit.pidev.Services;

import java.util.List;

import tn.esprit.pidev.Entities.Composition;

public interface ICompositionService {

    Composition saveComposition(Composition composition);

    List<Composition> getAllCompositions();

    Composition getCompositionById(long id);

    void deleteCompositionById(long id);

    Composition updateComposition(long id, Composition composition);

}