package tn.corilus.facture.Services;



import tn.corilus.facture.Entities.Facture;

import java.util.List;

public interface FactureServiceIMPL {
    void deleteFacture( Long id);
    Facture updateFacture(Facture facture);
    Facture getFactureById( Long id);
    List<Facture> getAllFacture();
    Facture createFacture( Facture facture);
}
