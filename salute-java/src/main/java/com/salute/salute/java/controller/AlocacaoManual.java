package com.salute.salute.java.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import org.kordamp.ikonli.javafx.FontIcon;

import com.salute.salute.java.Alocacao;
import com.salute.salute.java.AlocacaoSalaTurma;
import com.salute.salute.java.AlocarTurmas;
import com.salute.salute.java.Horario;
import com.salute.salute.java.HorarioSala;
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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class AlocacaoManual extends Controller implements Initializable {
    private SalaStore salaStore = SalaStore.getInstance();
    private TurmaStore turmaStore = TurmaStore.getInstance();
    private AlocacaoSalaTurmaStore alocacaoSalaTurmas = AlocacaoSalaTurmaStore.getInstance();
    // private ArrayList<Alocacao> horariosSalas = new ArrayList<>();
    private ArrayList<HorarioSala> horariosSalas = new ArrayList<>();
    private Popup popup;

    @FXML
    private TableView<HorarioSala> tabela;

    @FXML
    private TableColumn<HorarioSala, String> colunaSala;

    @FXML
    private TableColumn<HorarioSala, String> colunaHorario;

    @FXML
    private TableColumn<HorarioSala, String> colunaEstado;

    @FXML
    private TableColumn<HorarioSala, Void> colunaAcoes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        criarLista();

        colunaSala.setCellValueFactory((value) -> new SimpleStringProperty(value.getValue().getSalaStr()));
        colunaHorario.setCellValueFactory((value) -> new SimpleStringProperty(value.getValue().getHorarioStr()));
        colunaEstado.setCellValueFactory((value) -> new SimpleStringProperty(value.getValue().getEstado()));
        tabela.setItems(this.getAlocacoes());

        adicionaBotaoDesalocar();
    }

    private void criarLista() {
        horariosSalas.clear();
        Map<Integer, Sala> salas = this.salaStore.getSalas();
        for (Integer keySala : salas.keySet()) {
            Sala sala = salas.get(keySala);
            ArrayList<Horario> horarios = sala.getHorarios();
            for (int iHorario = 0; iHorario < horarios.size(); iHorario++) {
                // Turma turma = sala.getTurmaPorKey(iHorario);
                Turma turmaAlocada = alocacaoSalaTurmas.getTurmaAlocada(sala.getId(), horarios.get(iHorario).getId());
                if (turmaAlocada == null) {
                    // horariosSalas.add(new Alocacao(sala.toString(),
                    // horarios.get(iHorario).toString(),
                    // "Livre", sala, iHorario));
                    horariosSalas.add(new HorarioSala(sala, horarios.get(iHorario)));
                } else {
                    // horariosSalas.add(new Alocacao(sala.toString(),
                    // horarios.get(iHorario).toString(),
                    // turmaAlocada.toString(), sala, iHorario));
                    horariosSalas.add(new HorarioSala(sala, horarios.get(iHorario), turmaAlocada));
                }
            }
        }
    }

    private void atualizarLista() {
        criarLista();
        tabela.setItems(this.getAlocacoes());
    }

    private ObservableList<HorarioSala> getAlocacoes() {
        return FXCollections.observableArrayList(horariosSalas);
    }

    private ArrayList<Turma> getTurmas(Horario horario) {
        Map<Integer, Turma> turmas = this.turmaStore.getTurmas();
        ArrayList<Turma> turmasList = new ArrayList<>();
        for (Integer keyTurma : turmas.keySet()) {
            turmasList.add(turmas.get(keyTurma));
        }
        ArrayList<Turma> turmasHorario = new ArrayList<>();
        for (Turma turma : turmasList) {
            if (Boolean.TRUE.equals(turma.hasHorario(horario))
                    && Boolean.TRUE.equals(!turma.horarioIsAlocado(horario))) {
                turmasHorario.add(turma);
            }
        }
        return turmasHorario;
    }

    private void adicionaBotaoDesalocar() {
        Callback<TableColumn<HorarioSala, Void>, TableCell<HorarioSala, Void>> cellFactory = new Callback<TableColumn<HorarioSala, Void>, TableCell<HorarioSala, Void>>() {
            @Override
            public TableCell<HorarioSala, Void> call(final TableColumn<HorarioSala, Void> param) {
                return new TableCell<HorarioSala, Void>() {

                    private final Button btn = new Button();
                    private final Button btn2 = new Button();

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            HorarioSala alocacao = getTableView().getItems().get(getIndex());
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
                            HorarioSala alocacao = getTableView().getItems().get(getIndex());
                            // Node node = createPopup();
                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("../view/PopupAlocacaoManual.fxml"));
                            try {
                                Node node = loader.load();
                                PopupAlocacaoManual controller = loader.getController();
                                controller.setSelectSala(alocacao.getSala());
                                controller.setSelectHorario(alocacao.getHorario());
                                // Turma selectedTurma =
                                // alocacao.getSalaObj().getTurmaPorKey(alocacao.getHorarioIndex());
                                Turma selectedTurma = alocacao.getTurma();
                                // controller.setSelectTurmas(getTurmas(alocacao.getSalaObj().getHorarios()
                                // .get(alocacao.getHorarioIndex())), selectedTurma);
                                controller.setSelectTurmas(getTurmas(alocacao.getHorario()), selectedTurma);
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
