package com.salute.salute.java.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import org.kordamp.ikonli.javafx.FontIcon;

import com.salute.salute.java.AlocarTurmas;
import com.salute.salute.java.Horario;
import com.salute.salute.java.HorarioTurma;
import com.salute.salute.java.Sala;
import com.salute.salute.java.Turma;
import com.salute.salute.java.abstratta.Controller;
import com.salute.salute.java.singleton.AlocacaoSalaTurmaStore;
import com.salute.salute.java.singleton.SalaStore;
import com.salute.salute.java.singleton.TurmaStore;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class AlocacaoManualTurmas extends Controller implements Initializable {
    private SalaStore salaStore = SalaStore.getInstance();
    private TurmaStore turmaStore = TurmaStore.getInstance();
    private AlocacaoSalaTurmaStore alocacaoSalaTurmas = AlocacaoSalaTurmaStore.getInstance();
    // private ArrayList<Alocacao> horariosTurmas = new ArrayList<>();
    private ArrayList<HorarioTurma> horariosTurmas = new ArrayList<>();
    private Popup popup;

    @FXML
    private TableView<HorarioTurma> tabela;

    @FXML
    private TableColumn<HorarioTurma, String> colunaTurma;

    @FXML
    private TableColumn<HorarioTurma, String> colunaHorario;

    @FXML
    private TableColumn<HorarioTurma, String> colunaTipo;

    @FXML
    private TableColumn<HorarioTurma, String> colunaEstado;

    @FXML
    private TableColumn<HorarioTurma, Void> colunaAcoes;

    @FXML
    private ToggleButton toggleVisao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        criarLista();

        colunaTurma.setCellValueFactory((value) -> new SimpleStringProperty(value.getValue().getTurma().toString()));
        colunaHorario
                .setCellValueFactory((value) -> new SimpleStringProperty(value.getValue().getHorario().toString()));
        colunaTipo.setCellValueFactory(
                (value) -> new SimpleStringProperty(value.getValue().getHorario().getTipo().toString()));
        colunaEstado.setCellValueFactory((value) -> new SimpleStringProperty(value.getValue().getEstado()));
        tabela.setItems(this.getAlocacoes());

        adicionaBotaoDesalocar();

        toggleVisao.setSelected(true);

        toggleVisao.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == true) {
                return;
            }

            switchScene("AlocacaoManualSalas", tabela);
        });
    }

    private void criarLista() {
        horariosTurmas.clear();
        Map<Integer, Turma> turmas = this.turmaStore.getTurmas();
        System.out.println("Quantidade de turmas: " + turmas.size());
        for (Map.Entry<Integer, Turma> entry : turmas.entrySet()) {
            Turma turma = entry.getValue();
            for (Horario horario : turma.getHorarios()) {
                if (Boolean.TRUE.equals(turma.horarioIsAlocado(horario))) {
                    Sala salaAlocada = alocacaoSalaTurmas.getSalaAlocada(turma.getId(), horario.getId());
                    horariosTurmas.add(new HorarioTurma(turma, horario, salaAlocada));
                    continue;
                }
                horariosTurmas.add(new HorarioTurma(turma, horario));
            }
        }
    }

    private void atualizarLista() {
        criarLista();
        tabela.setItems(this.getAlocacoes());
    }

    private ObservableList<HorarioTurma> getAlocacoes() {
        return FXCollections.observableArrayList(horariosTurmas);
    }

    private ArrayList<Sala> getSalas(Horario horario) {
        Map<Integer, Sala> salas = this.salaStore.getSalas();
        ArrayList<Sala> salasList = new ArrayList<>();
        for (Map.Entry<Integer, Sala> entry : salas.entrySet()) {
            Sala sala = entry.getValue();
            if (!sala.hasHorario(horario.getId()) || alocacaoSalaTurmas.isOcupado(sala.getId(), horario.getId())) {
                continue;
            }
            salasList.add(sala);
        }
        return salasList;
    }

    private void adicionaBotaoDesalocar() {
        Callback<TableColumn<HorarioTurma, Void>, TableCell<HorarioTurma, Void>> cellFactory = new Callback<TableColumn<HorarioTurma, Void>, TableCell<HorarioTurma, Void>>() {
            @Override
            public TableCell<HorarioTurma, Void> call(final TableColumn<HorarioTurma, Void> param) {
                return new TableCell<HorarioTurma, Void>() {

                    private final Button btn = new Button();
                    private final Button btn2 = new Button();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            HorarioTurma alocacao = getTableView().getItems().get(getIndex());
                            boolean desalocou = AlocarTurmas.desalocarTurma(alocacao.getTurma(), alocacao.getSala(),
                                    alocacao.getHorario());

                            if (!desalocou) {
                                Notification.showNotification("Alocação Manual", "Não foi possível desalocar a turma.");
                                return;
                            }

                            Notification.showNotification("Alocação Manual", "Turma desalocada com sucesso.");

                            alocacaoSalaTurmas.atualizarFromAPI();
                            atualizarLista();
                        });
                    }

                    {
                        btn2.setOnAction((ActionEvent event) -> {
                            HorarioTurma alocacao = getTableView().getItems().get(getIndex());
                            // Node node = createPopup();
                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("../view/PopupAlocacaoManualTurmas.fxml"));
                            try {
                                Node node = loader.load();
                                PopupAlocacaoManualTurmas controller = loader.getController();
                                controller.setSelectTurmas(alocacao.getTurma());
                                controller.setSelectHorario(alocacao.getHorario());
                                // Turma selectedTurma =
                                // alocacao.getSalaObj().getTurmaPorKey(alocacao.getHorarioIndex());
                                Turma selectedTurma = alocacao.getTurma();
                                // controller.setSelectTurmas(getTurmas(alocacao.getSalaObj().getHorarios()
                                // .get(alocacao.getHorarioIndex())), selectedTurma);
                                ArrayList<Sala> salas = new ArrayList<>();
                                if (alocacao.getSala() != null) {
                                    salas.add(alocacao.getSala());
                                }
                                salas.addAll(getSalas(alocacao.getHorario()));
                                controller.setSelectSala(salas, alocacao.getSala());
                                controller.setCancelar(() -> {
                                    closePopup();
                                    atualizarLista();
                                });
                                openPopup(node);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            btn.getStyleClass().add("btn");
                            btn.getStyleClass().add("danger");
                            btn.getStyleClass().add("small");
                            btn2.getStyleClass().add("btn");
                            btn2.getStyleClass().add("primary");
                            btn2.getStyleClass().add("small");
                            FontIcon icon = new FontIcon();
                            icon.setIconLiteral("mdi-close");
                            btn.setGraphic(icon);
                            FontIcon icon2 = new FontIcon();
                            icon2.setIconLiteral("mdi-pencil");
                            icon2.setIconColor(Paint.valueOf("#fff"));
                            btn2.setGraphic(icon2);

                            HBox hbBtn = new HBox(btn, btn2);
                            hbBtn.setSpacing(8);
                            setGraphic(hbBtn);
                        }
                    }
                };
            }
        };

        colunaAcoes.setCellFactory(cellFactory);
    }

    private Stage getStage() {
        return (Stage) tabela.getScene().getWindow();
    }

    private void openPopup(Node node) {
        popup = new Popup();
        popup.setAutoFix(true);
        popup.setHideOnEscape(true);
        popup.getContent().add(node);
        popup.setOnHiding((e) -> {
            popup = null;
        });
        popup.getScene().getRoot().getStyleClass().add("z-elevation-4");
        popup.getScene().getRoot().getStyleClass().add("popup");
        popup.show(this.getStage());
    }

    private void closePopup() {
        if (popup != null) {
            popup.hide();
        }
    }
}
