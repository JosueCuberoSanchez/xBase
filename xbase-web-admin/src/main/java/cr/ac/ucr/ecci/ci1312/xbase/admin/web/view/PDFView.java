package cr.ac.ucr.ecci.ci1312.xbase.admin.web.view;


import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.procedure.ExerciseProcedureService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Answer;
import cr.ac.ucr.ecci.ci1312.xbase.model.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Josue Cubero on 09/07/2017.
 */
public class PDFView extends AbstractPdfView {
    /*@Override
    protected void buildPdfDocument(Map model,
                                    Document document, PdfWriter writer, HttpServletRequest req,
                                    HttpServletResponse resp) throws Exception {
        Exercise ex = (Exercise) model.get("generatePDF");
        Paragraph header = new Paragraph(new Chunk("Generate Pdf USing Spring Mvc"
                , FontFactory.getFont(FontFactory.HELVETICA, 30)));
        Paragraph by = new Paragraph(new Chunk("Author " + ex.getProblem()
                + " " + ex.getAverageRating(),FontFactory.getFont(FontFactory.HELVETICA, 20)));
        document.add(header);
        document.add(by);
    }*/

    @Autowired
    private ExerciseProcedureService exerciseProcedureService;

    @SuppressWarnings("unchecked")
    @Override
    protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest req,
                                    HttpServletResponse resp) throws Exception {
        Exercise ex = (Exercise) model.get("exercise");
        List<Answer> answers = (List) model.get("answers");
        Paragraph header = new Paragraph(new Chunk("Ejercicio xBase."
                , FontFactory.getFont(FontFactory.HELVETICA, 30)));
        Paragraph by = new Paragraph(new Chunk("Enunciado: \n" + ex.getProblem() + "\n" + "Respuestas y procedimientos(si aplican)\n", FontFactory.getFont(FontFactory.HELVETICA, 20)));
        document.add(header);
        document.add(by);
        int i = 1;
        for (Answer answer : answers) { //TEXT, IMAGE, NUMERIC, CODE
            Paragraph ans = null;
            Paragraph proc = null;
            switch (answer.getType()) {
                case TEXT:
                    ans = new Paragraph((new Chunk(i + ". " + answer.getContent() + "\n")));
                     /*if(answer.isPractical()){
                         proc = new Paragraph(new Chunk(this.exerciseProcedureService.getProcedure(answer).toString()));
                     }*/
                    break;
                case IMAGE:
                    ans = new Paragraph((new Chunk(i + ". " + answer.getImagePath() + "\n")));
                     /*if(answer.isPractical()){
                         proc = new Paragraph(new Chunk(this.exerciseProcedureService.getProcedure(answer).toString()));
                     }*/
                    break;
                case NUMERIC:
                    ans = new Paragraph((new Chunk(i + ". " + answer.getNumericalAnswer() + ", " + answer.getUnit() + "\n")));
                   /*if(answer.isPractical()){
                         proc = new Paragraph(new Chunk(this.exerciseProcedureService.getProcedure(answer).toString()));
                     }*/
                    break;
                case CODE:
                    ans = new Paragraph((new Chunk(i + ". " + answer.getSourceCode() + ", in " + answer.getProgrammingLanguage() + "\n")));
                     /*if(answer.isPractical()){
                         proc = new Paragraph(new Chunk(this.exerciseProcedureService.getProcedure(answer).toString()));
                     }*/
                    break;
            }
            document.add(ans);
            // document.add(proc);
        }
    }
}


