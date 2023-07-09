import { useEffect, useState } from 'react';
import Search from './Search';
import axios from 'axios';

const Gallery = () => {
  const [searchResults, setSearchResults] = useState<string[]>([]);
  const [randomResult, setRandomResult] = useState<string[]>([]);

  const [userInput, setUserInput] = useState('');
  const baseURL = `http://localhost:8080/api/cocktails/${userInput}`;
  const randomURL = 'http://localhost:8080/api/cocktails/random';

  useEffect(() => {
    const searchCocktail = async () => {
      try {
        if (userInput) {
          const response = await axios.get(baseURL);
          setSearchResults(response.data);
          // console.log(searchResults);
        }
        const randomReponse = await axios.get(randomURL);
        setRandomResult(randomReponse.data);
        // console.log(randomResult);
      } catch (error) {
        // console.log(error);
      }
    };
    searchCocktail();

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [baseURL, randomURL ]);

  return (
    <section className="gallery">
      <Search userInput={userInput} setUserInput={setUserInput} searchResults={searchResults} randomResult={randomResult}/>
    </section>
  );
};

export default Gallery;
