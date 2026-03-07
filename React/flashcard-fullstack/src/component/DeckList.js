import React from 'react';
import Deck from './Deck';

const DeckList = ({decks}) => {
    return (
       <div className="card-grid">
            {decks.map(deck=>{
                return <Deck deck={deck} key={deck.id}/>
            })}
        </div>
    );
}

export default DeckList;
