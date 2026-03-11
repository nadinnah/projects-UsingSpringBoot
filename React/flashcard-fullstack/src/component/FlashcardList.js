import React, { useState } from 'react';
import Flashcard from './Flashcard';
import { useParams } from 'react-router-dom';

const FlashcardList =({flashcards}) => {
    //useParams() always returns string
    const {deckId}=useParams()

    const[currentCardIndex,setCurrentCardIndex]= useState(0);

    const deckCards= flashcards.filter((flashcard) => {
       return flashcard.cardList.id===parseInt(deckId)}
    )
    
    
    if (deckCards.length === 0) {
    return <div>Loading cards...</div>
    }

    // useEffect(() => {
    // setCurrentCardIndex(0)
    // }, [deckId])

    function nextCard(){

        setCurrentCardIndex(prevIndex => (prevIndex + 1) % deckCards.length)
    }


    return (
        <div >
            <Flashcard flashcard={deckCards[currentCardIndex]} key={deckCards[currentCardIndex].id}/>
            <button onClick={nextCard}>
                 Next
            </button> 
            
            {/* {flashcards.map(flashcard=>{
                return <Flashcard flashcard={flashcard} key={flashcard.id}/>
            })} */}
        </div>
    );
}

export default FlashcardList;
