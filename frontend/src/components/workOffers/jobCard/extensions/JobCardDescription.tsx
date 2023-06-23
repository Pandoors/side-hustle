import React from "react";
import Image from "next/image";
import location from "../../../assets/images/Location.png";
import Calendar from "../../../assets/images/Calendar.png";
import Hourglass from "../../../assets/images/Hourglass empty.png";
import Time from "../../../assets/images/Time.png";
import Work from "../../../assets/images/Work.png";

interface offer {
  offerId: Number;
  description: string;
  location: string;
  startDate: string;
  endDate: string;
  jobType: string;
  formattedDuration: string;
  wage: string;
  longitude: Number;
  latitude: Number;
  fullName: string;
}

function JobCardDescription({ params }: { params: offer }) {
  return (
    <>
      <div className="job_description">
        <div className="job_description_title">Opis</div>
        <div className="job_description_text">{params.description}</div>
        {/* <div className="job_description_text">
          Jesteśmy renomowanym producentem piwa kraftowego poszukującym
          pasjonata piwa, który dołączy do naszego zespołu jako Tester Piwa
          Kraftowego.  Poszukujemy kogoś, kto będzie odpowiedzialny za analizę
          sensoryczną, ocenę jakości i doskonałości naszych piw kraftowych.
          Obowiązki: - Przeprowadzanie degustacji i analiz sensorycznych różnych
          rodzajów piwa kraftowego - Ocena jakości piwa pod względem smaku,
          zapachu, koloru i innych czynników sensorycznych - Dokonywanie oceny
          doskonałości piwa oraz identyfikowanie potencjalnych usprawnień -
          Sporządzanie szczegółowych raportów z przeprowadzonych testów i
          prezentowanie wyników zespołowi produkcyjnemu - Współpraca z zespołem
          produkcyjnym w celu opracowania strategii poprawy jakości piwa
          Wymagania: - Pasja do piwa kraftowego i szeroka wiedza na temat
          różnych stylów i technik warzenia - Doświadczenie w degustacji i
          ocenie sensorycznej piwa (mile widziane) - Zdolności analityczne i
          umiejętność opisywania wrażeń sensorycznych - Precyzja, dokładność i
          zdolność do pracy w zespole - Znajomość języka angielskiego na
          poziomie umożliwiającym komunikację Oferujemy: - Pracę w dynamicznym i
          rozwijającym się środowisku piwnym - Możliwość rozwijania umiejętności
          degustacyjnych i sensorycznych - Szansę współtworzenia i doskonalenia
          piw kraftowych o światowej klasie - Konkurencyjne wynagrodzenie i
          pakiet benefitów Jeśli jesteś entuzjastą piwa kraftowego i chcesz
          pracować w ekscytującej branży piwnej, zapraszamy do aplikowania na
          stanowisko Tester Piwa Kraftowego. Dołącz do naszego zespołu i pomóż
          nam dostarczać wyjątkowe doświadczenia smakowe naszym klientom!
        </div> */}
      </div>
    </>
  );
}

export default JobCardDescription;
