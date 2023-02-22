const quizData = [
    {
        question: "Which of the following is a verb?",
        a: "Car",
        b: "Run",
        c: "Desk",
        d: "Red",
        correct: "Run",
    },
    {
        question: "Which of the following is a pronoun?",
        a: "House",
        b: "They",
        c: "Book",
        d: "Jump",
        correct: "They",
    },
    {
        question: "Which sentence is grammatically correct?",
        a: "Me and him went to the store.",
        b: "He and I went to the store.",
        c: "Him and me went to the store.",
        d: "Me and he went to the store.",
        correct: "He and I went to the store.",
    },
    {
        question: "Which of the following is a preposition?",
        a: "Run",
        b: "With",
        c: "Desk",
        d: "Blue",
        correct: "With",
    },
    {
        question: "Which of the following is an adverb?",
        a: "Fast",
        b: "House",
        c: "Table",
        d: "Walk",
        correct: "Fast",
    },
    {
        question: "Which of the following is an adjective?",
        a: "Quickly",
        b: "Blue",
        c: "Jump",
        d: "He",
        correct: "Quickly",
    },
    {
        question: "Choose the correct form of the verb 'to be' for the sentence: 'They _________ happy.'",
        a: "Is",
        b: "Are",
        c: "Am",
        d: "Be",
        correct: "Are",
    },
    {
        question: "Which sentence is grammatically correct?",
        a: "I don't have no money.",
        b: "I don't have any money.",
        c: "I don't have some money.",
        d: "I don't have much money.",
        correct: "I don't have any money.",
    },
    {
        question: "Which of the following is a conjunction?",
        a: "Jump",
        b: "Or",
        c: "House",
        d: "Fast",
        correct: "Or",
    },
    {
        question: "Which of the following is an noun?",
        a: "Jumping",
        b: "Beautifully",
        c: "To",
        d: "In",
        correct: "Jumping",
    },
    {
        question: "Choose the correct form of the verb 'to be' for the sentence: 'She _________ my friend.'",
        a: "Is",
        b: "Are",
        c: "Am",
        d: "Be",
        correct: "Is",
    },
    {
        question: "Choose the correct option to complete the sentence: 'I _________ to the store yesterday.'",
        a: "Go",
        b: "Goes",
        c: "Went",
        d: "Going",
        correct: "Went",
    }, {
        question: "Choose the correct pronoun to complete the sentence: '_________ are the best team.'",
        a: "We",
        b: "I",
        c: "They",
        d: "You",
        correct: "They",
    },
    {
        question: "Which of the following is an adjective?",
        a: "Quickly",
        b: "Beautifully",
        c: "To",
        d: "In",
        correct: "Quickly",
    },
    {
        question: "Which of the following is an adverb?",
        a: "Loud",
        b: "Quickly",
        c: "Table",
        d: "Walk",
        correct: "Loud",
    },
    {
        question: "Choose the correct form of the verb 'to complete the sentence: 'He _________ a book when I saw him.'",
        a: "Read",
        b: "Reading",
        c: "Reads",
        d: "To read",
        correct: "Reading",
    },
    {
        question: "Which of the following is a conjunction?",
        a: "And",
        b: "Green",
        c: "Car",
        d: "Happy",
        correct: "And",
    },
    {
        question: "Which sentence is grammatically correct?",
        a: "I don't have no friends.",
        b: "I don't have any friends.",
        c: "I don't have some friends.",
        d: "I don't have much friends.",
        correct: "I don't have any friends.",
    },
    {
        question: "Which of the following is a noun?",
        a: "Dancing",
        b: "Happily",
        c: "Continue",
        d: "Occupation",
        correct: "Occupation"
    },
    {
        question: "Choose the correct option to complete the sentence: 'I always __________ coffee with breakfast.'",
        a: "drink",
        b: "drank",
        c: "drunk",
        d: "drinking",
        correct: "drink"
    },
    {
        question: "Choose the correct option to complete the sentence: 'Julia didn't come to the party, __________?'",
        a: "did she",
        b: "didn't she",
        c: "does she",
        d: "doesn't she",
        correct: "did she"
    },
    {
        question: "Choose the correct option to complete the sentence: 'My sister __________ a lot of books.'",
        a: "reads",
        b: "read",
        c: "reading",
        d: "has read",
        correct: "reads"
    },
    {
        question: "Choose the correct option to complete the sentence: 'I don't have __________ money to buy a car.'",
        a: "much",
        b: "many",
        c: "some",
        d: "any",
        correct: "any"
    },
    {
        question: "Choose the correct option to complete the sentence: 'Can you __________ me the salt, please?'",
        a: "pass",
        b: "take",
        c: "bring",
        d: "give",
        correct: "pass"
    },
    {
        question: "Choose the correct option to complete the sentence: 'Tom is __________ in playing the piano.'",
        a: "interested",
        b: "interesting",
        c: "interest",
        d: "interests",
        correct: "interested"
    },
    {
        question: "Choose the correct option to complete the sentence: 'She has a __________ job that pays well.'",
        a: "satisfy",
        b: "satisfied",
        c: "satisfying",
        d: "satisfaction",
        correct: "satisfying"
    },
    {
        question: "Choose the correct option to complete the sentence: 'I __________ my homework last night.'",
        a: "do",
        b: "did",
        c: "does",
        d: "have done",
        correct: "did"
    },
    {
        question: "Choose the correct option to complete the sentence: 'The library is __________ to the public.'",
        a: "open",
        b: "opened",
        c: "opening",
        d: "opens",
        correct: "open"
    },
    {
        question: "Choose the correct option to complete the sentence: 'I don't know __________ you are talking about.'",
        a: "that",
        b: "what",
        c: "which",
        d: "who",
        correct: "what"
    }
]
const answerEls = document.querySelectorAll('.answer')
const questionEl = document.getElementById('question')
const a_text = document.getElementById('a_text')
const b_text = document.getElementById('b_text')
const c_text = document.getElementById('c_text')
const d_text = document.getElementById('d_text')
const userId = document.getElementById('user_id').value
const submitBtn = document.getElementById('submit')
let currentQuiz = 0
let score = 0

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

        currentQuiz++

        if (currentQuiz < quizData.length) {
            loadQuiz()

        } else {
            fetch('http://localhost:8080/assessment', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },

                body: JSON.stringify({score})
            }).then(response => console.log('Success:'))

            quiz.innerHTML = `
          
            <h2>You answered   ${score}  / ${quizData.length} questions correctly</h2>
              <button href="/user">Finish</button>
     
            `
        }
    }
})
