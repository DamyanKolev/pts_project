package com.example.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.lang.Math;

import com.example.backend.dto.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

@Service
public class FunctionService {

    private DBAccess dbAccess = new DBAccess();

    // Make correlation analysis of the data
    public CADataDTO CorrelationAnalysis() {
        try (FileInputStream file = new FileInputStream(
                new ClassPathResource("data/Logs_Course A_StudentsActivities.xlsx").getFile());) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<GraphicData> graphicData = getGraphicData(sheet);

            int n = graphicData.size();
            int xySumMul = graphicData.stream().mapToInt(object -> object.getAssessment() + object.getWikiUpdates())
                    .sum();
            int x2 = graphicData.stream().mapToInt(object -> object.getAssessment() + object.getAssessment()).sum();
            int y2 = graphicData.stream().mapToInt(object -> object.getWikiUpdates() + object.getWikiUpdates()).sum();
            int xSum = graphicData.stream().mapToInt(object -> object.getAssessment()).sum();
            int ySum = graphicData.stream().mapToInt(object -> object.getWikiUpdates()).sum();

            double dependenceRes = (n * xySumMul - xSum * ySum)
                    / (Math.sqrt((n * x2 - xSum * xSum) * (n * y2 - ySum * ySum)));

            workbook.close();
            return new CADataDTO(getCorDependencyString(dependenceRes), graphicData);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    // Calculates frequency distribution
    public ArrayList<FDDataDTO> FrequencyDistribution() {
        try (FileInputStream file = new FileInputStream(
                new ClassPathResource("data/Logs_Course A_StudentsActivities.xlsx").getFile());) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            ArrayList<FDDataDTO> dataDTO = new ArrayList<FDDataDTO>();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            double sum;
            Cell sumCell = sheet.getRow(8).getCell(4);
            Cell exerciceCell = sheet.getRow(8).getCell(3);

            sumCell.setCellFormula(
                    "COUNTIFS(C2:C28090,\"File submissions\",B2:B28090,\"Assignment: Качване на Упр.*\")");
            evaluator.evaluateFormulaCell(sumCell);
            sum = sumCell.getNumericCellValue();

            for (int i = 1; i <= 5; i++) {
                String exerciseName = String.format("Упражнение %d", i);
                String exerciseFormula = String.format("Assignment: Качване на Упр. %d", i);
                String formula = String.format("COUNTIFS(C2:C28090,\"File submissions\",B2:B28090,\"%s\")",
                        exerciseFormula);

                exerciceCell.setCellFormula(formula);
                evaluator.clearAllCachedResultValues();
                evaluator.evaluateFormulaCell(exerciceCell);

                double value = exerciceCell.getNumericCellValue();
                dataDTO.add(new FDDataDTO(exerciseName, value, (value / sum * 100)));
            }

            dataDTO.add(new FDDataDTO("Общо", sum, sum));

            workbook.close();

            return dataDTO;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    // Calculates mode, medeian and average value
    public MCTDataDTO MeasuresOfTheCentralTrend() {
        try (FileInputStream file = new FileInputStream(
                new ClassPathResource("data/Logs_Course A_StudentsActivities.xlsx").getFile());) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<TableData> tableData = getTableData(sheet);
            int mode;
            double averageValue, median;
            String values = tableData.stream()
                    .map(object -> String.valueOf(object.getExercises()))
                    .collect(Collectors.joining(",", "(", ")"));

            averageValue = getDataFromFormula(workbook, String.format("AVERAGE%s", values));
            median = getDataFromFormula(workbook, String.format("MEDIAN%s", values));
            mode = (int) getDataFromFormula(workbook, String.format("MODE%s", values));

            workbook.close();
            return new MCTDataDTO(averageValue, median, mode, tableData);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    // Calculates deviation, dispersion and scope
    public DMDataDTO DistractionMeasures() {
        try (FileInputStream file = new FileInputStream(
                new ClassPathResource("data/Logs_Course A_StudentsActivities.xlsx").getFile());) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<TableData> tableData = getTableData(sheet);
            double deviation, dispersion, scope;
            String values = tableData.stream()
                    .map(object -> String.valueOf(object.getExercises()))
                    .collect(Collectors.joining(",", "(", ")"));

            deviation = getDataFromFormula(workbook, String.format("STDEV%s", values));
            dispersion = getDataFromFormula(workbook, String.format("VAR%s", values));
            scope = getDataFromFormula(workbook, String.format("MAX%s-MIN%s", values, values));

            workbook.close();
            return new DMDataDTO(deviation, dispersion, scope, tableData);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public void ReadingAndSummarizing(MultipartFile file) throws IOException {
        // Resource resource = new ClassPathResource(file.);
        InputStream fileStream = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(fileStream);
        var sheet = workbook.getSheetAt(0);

        workbook.close();
    }

    private ArrayList<TableData> getTableData(XSSFSheet sheet) {
        ArrayList<TableData> data = new ArrayList<TableData>();
        HashMap<Integer, Integer> students = new HashMap<Integer, Integer>();

        for (Row row : sheet) {

            if (row.getCell(1).toString().startsWith("Assignment: Качване на Упр.") &&
                    row.getCell(2).toString().equals("File submissions")) {
                int studentId = Integer.parseInt(row.getCell(4).toString().split("'")[1]);

                if (students.containsKey(studentId)) {
                    students.put(studentId, students.get(studentId) + 1);
                } else {
                    students.put(studentId, 1);
                }
            }
        }

        for (HashMap.Entry<Integer, Integer> set : students.entrySet()) {
            // Printing all elements of a Map
            data.add(new TableData(set.getKey(), set.getValue()));
        }

        return data;
    }

    private double getDataFromFormula(XSSFWorkbook workbook, String formula) {
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        XSSFSheet sheet = workbook.getSheetAt(0);
        Cell resultCell = sheet.getRow(8).getCell(4);

        resultCell.setCellFormula(formula);
        evaluator.evaluateFormulaCell(resultCell);

        return resultCell.getNumericCellValue();
    }

    private ArrayList<GraphicData> getGraphicData(XSSFSheet sheet) {
        ArrayList<GraphicData> data = new ArrayList<GraphicData>();
        HashMap<Integer, Integer> students = new HashMap<Integer, Integer>();
        HashMap<Integer, GraphicData> result = new HashMap<Integer, GraphicData>();

        for (Row row : sheet) {

            if (row.getCell(2).toString().startsWith("Wiki") &&
                    row.getCell(3).toString().equals("Wiki page updated")) {
                int studentId = Integer.parseInt(row.getCell(4).toString().split("'")[1]);

                if (students.containsKey(studentId)) {
                    students.put(studentId, students.get(studentId) + 1);
                } else {
                    students.put(studentId, 1);
                }
            }
        }

        getWikiAssessmentReports("data/Course A_StudentsResults_Year 1.xlsx", students, result);
        getWikiAssessmentReports("data/Course A_StudentsResults_Year 2.xlsx", students, result);

        for (HashMap.Entry<Integer, GraphicData> set : result.entrySet()) {
            // Printing all elements of a Map
            data.add(set.getValue());
        }

        return data;
    }

    private HashMap<Integer, GraphicData> getWikiAssessmentReports(String filePath, HashMap<Integer, Integer> data,
            HashMap<Integer, GraphicData> result) {
        try (FileInputStream file = new FileInputStream(
                new ClassPathResource(filePath).getFile());) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell idCell = row.getCell(0);
                Cell resCell = row.getCell(1);

                if (idCell == null || resCell == null) {
                    break;
                }

                if (idCell.getCellType() == CellType.STRING && resCell.getCellType() == CellType.STRING) {
                    continue;
                } else {
                    int id = (int) idCell.getNumericCellValue();
                    int res = (int) resCell.getNumericCellValue();

                    if (data.containsKey(id) && !result.containsKey(id)) {
                        result.put(id, new GraphicData(res, data.get(id)));
                    }
                }

            }

            workbook.close();
            return result;

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    private String getCorDependencyString(double cor) {
        String dependency;

        if (cor == 0.0d) {
            dependency = "Липсва зависимост";
        } else if (cor < 0.3d) {
            dependency = "Зависимостта е слаба";
        } else if (cor < 0.5d) {
            dependency = "Умерена зависимост";
        } else if (cor < 0.7d) {
            dependency = "Значителна зависимост";
        } else if (cor < 0.9d) {
            dependency = "Силна зависимост";
        } else if (cor < 1) {
            dependency = "Много силна зависимост";
        } else {
            dependency = "Зависимостта е функционална";
        }

        return dependency;
    }
}
