/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andik.penjualan.controller;

import com.andik.penjualan.entity.JenisPelanggan;
import com.andik.penjualan.entity.Pelanggan;
import com.andik.penjualan.service.PelangganService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author andik
 */
@Named
@ViewScoped
public class PelangganList implements Serializable {

    @EJB
    private PelangganService pelangganService;

    private List<Pelanggan> pelanggans;
    private UploadedFile file;

    @PostConstruct
    public void init() {
        load();
    }

    public void load() {
        pelanggans = pelangganService.findAll();
    }

    public List<Pelanggan> getPelanggans() {
        return pelanggans;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void handleUpload(FileUploadEvent event) {
        try {
            UploadedFile uploadedFile = event.getFile();
            final String uploadPath = "c:/uploads";
            File temp = new File(uploadPath);
            if (!temp.exists()) {
                temp.mkdirs();
            }

            Path folder = Paths.get(uploadPath);
            String filename = FileNameUtils.getBaseName(uploadedFile.getFileName());
            String ext = FileNameUtils.getExtension(uploadedFile.getFileName());
            Path path = Files.createTempFile(folder, filename + ".", "." + ext);
            Files.copy(uploadedFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            List<Pelanggan> toImports = readFileExcel(path.toFile());
            for (Pelanggan toImport : toImports) {
                pelangganService.create(toImport);
            }
            load();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
        }
    }
    private static final Logger LOG = Logger.getLogger(PelangganList.class.getName());

    private List<Pelanggan> readFileExcel(File sourceFileExcel) throws FileNotFoundException, IOException, InvalidFormatException {
        Workbook workbook = new XSSFWorkbook(sourceFileExcel);
        Sheet sheetAt = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheetAt.iterator();
        List<Pelanggan> toImports = new ArrayList<>();

        int rowIndex = 0;
        while (iterator.hasNext()) {
            Row row = iterator.next();
            if (rowIndex >= 1) {
                Pelanggan pelanggan = new Pelanggan(UUID.randomUUID().toString());
                Iterator<Cell> cell = row.iterator();
                int colIndex = 0;
                while (cell.hasNext()) {
                    Cell currentCell = cell.next();
                    switch (colIndex) {
                        case 0: {
                            String nama = currentCell.getStringCellValue();
                            pelanggan.setNama(nama);
                            break;
                        }

                        case 1: {
                            String namaJenis = currentCell.getStringCellValue();
                            JenisPelanggan jenisPelanggan = pelangganService.getOrCreateJenisPelanggan(namaJenis);
                            pelanggan.setJenisPelanggan(jenisPelanggan);
                            break;
                        }
                        
                        case 2: {
                            String alamat = currentCell.getStringCellValue();
                            pelanggan.setAlamat(alamat);
                            break;
                        }
                    }
                    
                    colIndex++;
                }
                
                toImports.add(pelanggan);
            }
            
            rowIndex++;
        }
        
        sourceFileExcel.delete();
        return toImports;
    }

}
