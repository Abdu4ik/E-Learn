package com.tafakkoor.englishlearningplatform.service;

import com.tafakkoor.englishlearningplatform.dao.*;
import com.tafakkoor.englishlearningplatform.domains.*;
import com.tafakkoor.englishlearningplatform.utils.validator.GrammarValidator;
import com.tafakkoor.englishlearningplatform.utils.validator.QuestionValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.lang.ThreadLocal.withInitial;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class TeacherService {
    private static final ThreadLocal<TeacherService> instance = ThreadLocal.withInitial(TeacherService::new);


    public static TeacherService getInstance() {
        return instance.get();
    }

    public Story getStoryById(int id) {
        return new StoryDAO().findById(id);
    }

    public List<Vocabulary> getVocabulariesByStory(int id) {
        return new VocabularyDAO().getVocabulariesByStoryId(getStoryById(id));
    }

    public List<Story> getAllStories() {
        return new StoryDAO().findAllStories();
    }

    public List<Grammar> getAllGrammars() {
        return new GrammarDAO().findAllGrammars();
    }

    public void saveQuestion(Questions questions) {
        QuestionDAO questionsDAO = new QuestionDAO();
        questionsDAO.save(questions);

    }

    public void saveVariant(Variants variants) {
        VariantDAO variantDAO = new VariantDAO();
        variantDAO.save(variants);
    }

    public Grammar getGrammarById(int grammarId) {


        return new GrammarDAO().findById(grammarId);
    }

    public void deleteWord(int id) {
        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        Vocabulary vocabulary = vocabularyDAO.findById(id);
        vocabulary.setDeleted(true);
        vocabularyDAO.save(vocabulary);
    }

    public void updateAsDelete(Story story) {
        StoryDAO storyDAO = new StoryDAO();
        storyDAO.update(story);
    }
    public void updateAsDelete(Grammar grammar) {
        GrammarDAO grammarDAO = new GrammarDAO();
        grammarDAO.update(grammar);
    }
    public void updateStory(Story story) {
        StoryDAO storyDAO = new StoryDAO();
        storyDAO.update(story);
    }

    public List<Questions> getGrammarQuestions(Grammar grammar) {
        QuestionDAO questionDAO = new QuestionDAO();
        Questions questions = new Questions();

        return questionDAO.findAllByGrammarId(grammar.getId());

    }

    public void updateGrammar(Grammar grammar) {
        GrammarDAO grammarDAO = new GrammarDAO();
        grammarDAO.update(grammar);
    }

    public Questions getQuestionById(int questionId) {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.findById(questionId);
    }

    public List<Variants> getVariantsByQuestionId(int id) {

        Questions questions=new Questions();

        return new VariantDAO().findAllByQuestionId(id);
    }

    public void updateQuestion(Questions questions) {
        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.update(questions);
    }

    public void updateVariant(Variants variants) {
        VariantDAO variantDAO = new VariantDAO();
        variantDAO.update(variants);
    }

    public void deleteVariantsByQuestionId(Integer id) {
        VariantDAO variantDAO = new VariantDAO();
        variantDAO.deleteVariantsByQuestionId(id);
    }

    public void deleteQuestion(int id) {
        QuestionDAO questionDAO = new QuestionDAO();
        Questions questions = questionDAO.findById(id);
        deleteVariantsByQuestionId(questions.getId());
        questionDAO.delete(questions);
    }

    public void validateGrammar(HttpServletRequest req) throws Exception {
        GrammarValidator validator = new GrammarValidator();
        validator.validate(req);
    }

    public void validateQuestion(HttpServletRequest req) throws Exception {
        QuestionValidator validator = new QuestionValidator();
        validator.validate(req);
    }

    public void saveStory(Story story) {
        StoryDAO storyDAO = new StoryDAO();
        storyDAO.save(story);
    }
}
