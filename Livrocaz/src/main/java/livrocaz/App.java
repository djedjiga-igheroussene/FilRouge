package livrocaz;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import livrocaz.model.Auteur;
import livrocaz.model.Authorities;
import livrocaz.model.Client;
import livrocaz.model.Commande;
import livrocaz.model.Editeur;
import livrocaz.model.Livre;
import livrocaz.model.Users;
import livrocaz.model.Genre;
import livrocaz.model.Langue;
import livrocaz.model.LigneDeCommande;
import livrocaz.repository.AuteurRepository;
import livrocaz.repository.AuthoritiesRepository;
import livrocaz.repository.ClientRepository;
import livrocaz.repository.CommandeRepository;
import livrocaz.repository.EditeurRepository;
import livrocaz.repository.GenreRepository;
import livrocaz.repository.LangueRepository;
import livrocaz.repository.LigneDeCommandeRepository;
import livrocaz.repository.LivreRepository;
import livrocaz.repository.UserRepository;

@SpringBootApplication
public class App implements CommandLineRunner {

    //.....Autowired des Repository....//

    @Autowired
    private LivreRepository livreRepo;

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private AuteurRepository auteurRepo;

    @Autowired
    private GenreRepository genreRepo;

    @Autowired
    private LangueRepository langueRepo;
    
    @Autowired
    private EditeurRepository editeurRepo;
    
    @Autowired
    private LigneDeCommandeRepository ligneCommandeRepo;
    
    @Autowired
    private CommandeRepository commandeRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private AuthoritiesRepository AuthRepo;

    //.....etc......//

    public static void main(String[] args)  {
        SpringApplication.run(App.class, args);
    }

    public void run(String... args) throws Exception {

    	
    	ligneCommandeRepo.deleteAll();
    	commandeRepo.deleteAll();
        livreRepo.deleteAll();;
        clientRepo.deleteAll();
        auteurRepo.deleteAll();
        genreRepo.deleteAll();
        langueRepo.deleteAll();
        editeurRepo.deleteAll();
        AuthRepo.deleteAll();
        userRepo.deleteAll();
        AuthRepo.deleteAll();        

        //.....etc........//


       Livre fondation = new Livre();
       Livre robots = new Livre();
       Auteur azimov = new Auteur();
       Collection<Auteur>hashAuteurs = new HashSet<Auteur>();
       Collection<Livre>hashLivres = new ArrayList<Livre>();
       fondation.setAnneeParution("1986");
       fondation.setDescriptionLivre("desc fondation");
       fondation.setImageCouverture("fondation couv");
       fondation.setIsbn("1111");
       fondation.setPrixNeuf(10);
       fondation.setPrixOccas(5);
       fondation.setStock(20);
       fondation.setSujetLivre("Sujet de Fondation");
       fondation.setTitreLivre("Fondation");
       fondation =livreRepo.save(fondation);

       robots.setAnneeParution("1975");
       robots.setDescriptionLivre("desc robots");
       robots.setImageCouverture("robots couv");
       robots.setIsbn("1112");
       robots.setPrixNeuf(6);
       robots.setPrixOccas(3);
       robots.setStock(10);
       robots.setSujetLivre("Sujet de robots");
       robots.setTitreLivre("robots");
       robots=livreRepo.save(robots);


       azimov.setNameAuteur("Azimov");
       azimov.setSurnameAuteur("Isaac");
       azimov= auteurRepo.save(azimov);

       hashAuteurs.add(azimov);
       fondation.setAuteurs(hashAuteurs);
       robots.setAuteurs(hashAuteurs);
       hashLivres.add(robots);
       hashLivres.add(fondation);
       azimov.setLivres(hashLivres);


      Genre roman = new Genre();
      Genre sf = new Genre();
      Collection<Genre> genres = new HashSet<Genre>();
      roman.setNomGenre("Roman");
      sf.setNomGenre("Science-fiction");
      roman = genreRepo.save(roman);
      sf = genreRepo.save(sf);
      genres.add(roman);
      genres.add(sf);
      fondation.setGenres(genres);
      robots.setGenres(genres);
      roman.setLivres(hashLivres);
      sf.setLivres(hashLivres);

      Langue fr = new Langue();
      fr.setNomLangue("Francais");
      fr = langueRepo.save(fr);
      fondation.setLangue(fr);
      robots.setLangue(fr);
      
      Editeur gp = new Editeur();
      gp.setNomEditeur("Gnome Press");
      gp = editeurRepo.save(gp);
      fondation.setEditeur(gp);
      robots.setEditeur(gp);
      
      LigneDeCommande lc = new LigneDeCommande();
      lc.setQuantite(1);
      lc.setLivre(robots);
      lc = ligneCommandeRepo.save(lc);

      langueRepo.save(fr);
      genreRepo.save(roman);
      genreRepo.save(sf);
      livreRepo.save(fondation);
      livreRepo.save(robots);
      auteurRepo.save(azimov);
      
      Users us1 = new Users();
      us1.setUsername("toto33");
      us1.setPassword("{bcrypt}$2a$04$3oa5XGzGArd2DnRv3.ax7OxGxnvCisSuWWGxYM2xNE99UFLCgQXYS");
      us1.setEnabled(1);
      userRepo.save(us1);
      
      Users us2 = new Users();
      us2.setUsername("Riri77");
      us2.setPassword("{bcrypt}$2a$04$CZsnHi2Jg/Z0dmBWEE3BKehk9MkLQsQAMtVgsepayT1WdIEx5GTIq");
      us2.setEnabled(1);
      userRepo.save(us2);
      
      Users us3 = new Users();
      us3.setUsername("Loulou88");
      us3.setPassword("{bcrypt}$2a$04$L81ltvjTKE57lMNPMC3TQeDAPtTBmoxcclRsfDVt.u7uUCHHLSmMO");
      us3.setEnabled(1);
      userRepo.save(us3);
      
      Authorities rol = new Authorities();
      rol.setUsers(us1);
      rol.setAuthority("INSCRIT");
      AuthRepo.save(rol);
      
      Authorities rol2 = new Authorities();
      rol2.setUsers(us2);
      rol2.setAuthority("GESTIONNAIRE");
      AuthRepo.save(rol2);
      
      Authorities rol3 = new Authorities();
      rol3.setUsers(us3);
      rol3.setAuthority("ADMIN");
      AuthRepo.save(rol3);
      
      Client c1 = new Client("toto", "tata", 1, "Rue Hoche", "Bat A", 75000, "Paris", "toto@toto", us1);
      clientRepo.save(c1);
      
      Commande cmd = new Commande();
      cmd.setClient(c1);
      cmd.setDate("12/12/2019");
      cmd.setFraisDePort(20.2);
      cmd.setTva(5.5);
      commandeRepo.save(cmd);
      lc.setCommande(cmd);
      ligneCommandeRepo.save(lc);
      
      
      
      
      
    }
}