const quiz = document.getElementById('quiz')
const grammar_id = document.getElementById('grammar_id').value
const answerEls = document.querySelectorAll('.answer')
const questionEl = document.getElementById('question')
const a_text = document.getElementById('a_text')
const b_text = document.getElementById('b_text')
const c_text = document.getElementById('c_text')
const d_text = document.getElementById('d_text')
const submitBtn = document.getElementById('submit')
const body = document.getElementById('body')
const current_quiz = document.getElementById('current_quiz')
let currentQuiz = document.getElementById('current_quiz').value
let score = 0
let quizData = (function getQuestions() {
    fetch('http://localhost:8080/questions/get/' + grammar_id)
        .then(response => response.json())
        .then(json => {
            quizData = json
            loadQuiz()
        })
});

function deselectAnswers() {
    answerEls.forEach(answerEl => answerEl.checked = false)
}

function getSelected() {
    let answer = null
    answerEls.forEach(answerEl => {
        if (answerEl.checked) {
            answer = answerEl.id
            console.log(answer)
        }
    })
    return answer
}


function loadQuiz() {
    deselectAnswers()
    document.getElementById('questionId').value = quizData[currentQuiz].questionId
    questionEl.innerText = quizData[currentQuiz].question
    a_text.innerText = quizData[currentQuiz].a
    b_text.innerText = quizData[currentQuiz].b
    c_text.innerText = quizData[currentQuiz].c
    d_text.innerText = quizData[currentQuiz].d
}


submitBtn.addEventListener('click', () => {
    let answer = getSelected()
    if (answer) {
        if (answer === quizData[currentQuiz].correct) {
            score++
        }
        document.getElementById('choice').value = answer

        currentQuiz++
        current_quiz.value = currentQuiz
        if (currentQuiz < quizData.length) {
            loadQuiz()
        } else {
            // fetch('http://localhost:8080/grammar/test', {
            //     method: 'POST',
            //     headers: {
            //         'Accept': 'application/json',
            //         'Content-Type': 'application/json'
            //     },
            //     body: JSON.stringify({ userAnswers})
            // }).then(response => console.log('Success:'))

            quiz.innerHTML = `
          
            <h2>You answered   ${score}  / ${quizData.length} questions correctly</h2>
              <button onclick="location.reload()" >Reload</button>
     
            `
        }
    }
})
