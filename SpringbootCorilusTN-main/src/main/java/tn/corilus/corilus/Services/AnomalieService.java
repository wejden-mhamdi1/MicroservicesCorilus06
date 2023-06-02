package tn.corilus.corilus.Services;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import tn.corilus.corilus.Entities.Anomalie;
import tn.corilus.corilus.Repository.AnomalieRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service

public class AnomalieService implements CommandLineRunner{


        private AnomalieRepository anomalieRepository;

        public AnomalieService(AnomalieRepository anomalieRepository) {
            this.anomalieRepository = anomalieRepository;
        }

        @Override
        public void run(String... args) throws Exception {

            List<Anomalie> entities = readExcelFile();



        }
    private List<Anomalie> readExcelFile() throws IOException, InvalidFormatException {
        List<Anomalie> entities = new ArrayList<>();

        File file = new File("./SpringbootCorilusTN-main/errors/codes_erreur_list.xlsx");

        // Créer une instance de Workbook pour lire le fichier Excel
        Workbook workbook = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            // Remplacez les espaces par "%20" dans le chemin du fichier
            String encodedFilePath = file.toURI().toString().replace(" ", "");

            // Vérifier si le fichier est au format XLS ou XLSX
            if (encodedFilePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fileInputStream);
            }
            else {
                throw new IllegalArgumentException("Le fichier doit être au format Excel.");
            }

            // ... Le reste de votre code pour lire le fichier Excel

        } catch (IOException e) {
            e.printStackTrace();
        }


        // Récupérer la feuille de calcul souhaitée
        Sheet sheet = workbook.getSheetAt(0);

        // Parcourir les lignes
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Anomalie entity = new Anomalie();

                Double majBijw = getCellDoubleValue(row.getCell(0));
                entity.setMajBijw(majBijw != null ? majBijw : 0.0);

                Double modifType = getCellDoubleValue(row.getCell(1));
                entity.setModifType(modifType != null ? modifType : 0.0);

                entity.setErrorNature(getCellStringValue(row.getCell(2)));
                entity.setCodeError(getCellStringValue(row.getCell(3)));
                entity.setBelgeDescription(getCellStringValue(row.getCell(4)));
                entity.setFrenshDescription(getCellStringValue(row.getCell(5)));
                entity.setRemarque(getCellStringValue(row.getCell(6)));

                entities.add(entity);

                anomalieRepository.save(entity);
            }
        }

        // Fermer la lecture du fichier
        workbook.close();

        return entities;
    }




    private String getCellStringValue(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else {
            return null;
        }
    }
    private byte[] getCellBytesValue(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.STRING) {
            String cellValue = cell.getStringCellValue();
            return cellValue.getBytes();
        } else {
            return null;
        }
    }



    // Méthode utilitaire pour récupérer la valeur d'une cellule de type Double
        private Double getCellDoubleValue(Cell cell) {
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                return cell.getNumericCellValue();
            } else {
                return null;
            }
        }





}

