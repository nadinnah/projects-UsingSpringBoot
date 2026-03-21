import React, { useState } from 'react';
import Flashcard from './Flashcard';
import {Link, useParams } from 'react-router-dom';

const FlashcardList =({flashcards}) => {
    //useParams() always returns string
    const {deckId}=useParams()


    const[currentCardIndex,setCurrentCardIndex]= useState(0);

    const deckCards= flashcards.filter((flashcard) => {
        console.log(flashcard)
       return flashcard.deck.id===parseInt(deckId)}
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
        <div>
            <Flashcard flashcard={deckCards[currentCardIndex]} key={deckCards[currentCardIndex].id}/>
            
            <div className='d-flex justify-content-center'>
                <button className='btn btn-light ' onClick={nextCard}>
                    Next
                </button> 
                <Link className="btn btn-light ms-5" to={`/${deckId}/add_card`}>Add New Card</Link>
            </div>
            
            {/* {flashcards.map(flashcard=>{
                return <Flashcard flashcard={flashcard} key={flashcard.id}/>
            })} */}
        </div>
    );
}

export default FlashcardList;
