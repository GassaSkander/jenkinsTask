package tn.esprit.rh.achat;


import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;
import tn.esprit.rh.achat.services.IFournisseurService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FournisseurTest {

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private FactureRepository factureRepository;

    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;

    @InjectMocks
    private FournisseurServiceImpl fournisseurServiceImp;



    @Test
    @Order(1)
    void testRetrieveAllFournisseurs() {
        ArrayList<Fournisseur> mockFournisseurs = new ArrayList<>();

        // behaviour of fournisseurRepository
        when(fournisseurRepository.findAll()).thenReturn(mockFournisseurs);

        // call retrieveAllFournisseurs
        List<Fournisseur> result = fournisseurServiceImp.retrieveAllFournisseurs();

        // verify result
        assertEquals(mockFournisseurs.size(), result.size());
        assertSame(mockFournisseurs, result);
        assertTrue(result.isEmpty());
        verify(fournisseurRepository).findAll();
    }

    @Test
    @Order(1)
    void addFournisseur_success(){
        // create fournisseur
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCode("F001");
        fournisseur.setLibelle("sample Fournisseur");
        fournisseur.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);

        Mockito.when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);
        Fournisseur savedFournisseur = fournisseurRepository.save(fournisseur);
        assertNotNull(savedFournisseur);
    }

    @Test
    @Order(2)
    void addFournisseur_failure() {
        // create fournisseur
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCode("F002");  // Ensure this code is unique to trigger a failure
        fournisseur.setLibelle("another Fournisseur");
        fournisseur.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);

        // Stub the save method to return null (simulating a failure)
        Mockito.when(fournisseurRepository.save(fournisseur)).thenReturn(null);

        Fournisseur savedFournisseur = fournisseurRepository.save(fournisseur);

        // Verify that the savedFournisseur is null, indicating a failure
        assertNull(savedFournisseur);

    }

    @Test
    @Order(3)
    void updateFournisseur_success(){
        List<Fournisseur> fournisseurs = fournisseurServiceImp.retrieveAllFournisseurs();
        // Check that the list is not null and not empty
        Fournisseur f = new Fournisseur();
        fournisseurs.add(f);
        assertNotNull(fournisseurs);
        assertFalse(fournisseurs.isEmpty());

        // create a Fournisseur

        Fournisseur fournisseur = fournisseurs.get(0);
        // Update some properties of the first Fournisseur
        fournisseur.setCode("F002");
        fournisseur.setLibelle("Updated Fournisseur");

        // Stub the save method to return the updated Fournisseur
        Mockito.when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);
        // Call the save method to update the Fournisseur
        Fournisseur updatedFournisseur = fournisseurRepository.save(fournisseur);

        // Verify that the updatedFournisseur is not null
        assertNotNull(updatedFournisseur);

        // Verify that the fields have been updated as expected
        assertEquals("F002", updatedFournisseur.getCode());
        assertEquals("Updated Fournisseur", updatedFournisseur.getLibelle());

    }

    @Test
    @Order(4)
    void updateFournisseur_failure() {
        // Call the retrieveAllFournisseurs method to get a list of Fournisseurs
        List<Fournisseur> fournisseurs = fournisseurServiceImp.retrieveAllFournisseurs();

        // Check that the list is not null and not empty
        assertNotNull(fournisseurs);
        assertFalse(fournisseurs.isEmpty());

        // Retrieve the first Fournisseur from the list
        Fournisseur fournisseur = fournisseurs.get(0);

        // Update some properties of the first Fournisseur
        fournisseur.setCode("F002");
        fournisseur.setLibelle("Attempted Update");

        // Stub the save method to return null (simulating a failure)
        Mockito.when(fournisseurRepository.save(fournisseur)).thenReturn(null);

        // Call the save method to attempt an update
        Fournisseur updatedFournisseur = fournisseurRepository.save(fournisseur);

        // Verify that the updatedFournisseur is null, indicating a failure
        assertNull(updatedFournisseur);
    }


}
