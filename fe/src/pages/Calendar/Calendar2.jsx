import { useEffect, useState } from "react";
import styled from "styled-components";
import Calendar from "../../components/Calendar/Schedule";
import BlockModal from "../../components/Calendar/BlockModal";
import { useRecoilValue } from "recoil";
import { modalState } from "../../components/atoms";
import { AnimatePresence } from "framer-motion";
export default function Calendar2() {
  const now = new Date();
  const [year, setYear] = useState(now.getFullYear());
  const [month, setMonth] = useState(now.getMonth());
  const [weeks, setWeeks] = useState();
  const modalOpen = useRecoilValue(modalState);
  useEffect(() => {
    makeCalendar(year, month);
  }, [year, month]);
  const makeCalendar = (year, month) => {
    const LASTDATE = {
      1: 31,
      2: (year % 4 === 0 && year % 100 !== 0) || year % 400 === 0 ? 29 : 28,
      3: 31,
      4: 30,
      5: 31,
      6: 30,
      7: 31,
      8: 31,
      9: 30,
      10: 31,
      11: 30,
      12: 31,
    };
    const firstDay = new Date(year, month, 1).getDay();
    const lastDate = LASTDATE[month + 1];
    let date = 1;
    let newWeeks = [];
    for (let week = 0; week < 6; week++) {
      let newWeek = [];
      for (let day = 0; day < 7; day++) {
        if (date > lastDate || (week === 0 && firstDay > day)) {
          newWeek.push({ id: null, value: "" });
        } else {
          const id =
            String(year) +
            String(month + 1).padStart(2, 0) +
            String(date).padStart(2, 0);

          newWeek.push({
            id: id,
            value: date,
          });
          date++;
        }
      }
      newWeeks.push(newWeek);
    }
    setWeeks(newWeeks);
  };
  const onBtnPress = (title) => {
    const prevMonth = month;
    if (title === "prev") {
      setYear(prevMonth === 0 ? year - 1 : year);
      setMonth(prevMonth === 0 ? 11 : prevMonth - 1);
    } else if (title === "next") {
      setYear(prevMonth === 11 ? year + 1 : year);
      setMonth(prevMonth === 11 ? 0 : prevMonth + 1);
    }
  };
  return (

    <Container>
      <Header>
        <MonthButton onClick={() => onBtnPress("prev")}>◀</MonthButton>
        <span>
          {year}-{String(month + 1).padStart(2, 0)}
        </span>
        <MonthButton onClick={() => onBtnPress("next")}>▶</MonthButton>
      </Header>
      <Calendar weeks={weeks} />
      {modalOpen.state && <BlockModal />}
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  background-color: #ffffff;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
`;
const Header = styled.div`
  flex-direction: row;
  margin-block: 10px;
  
`;
const MonthButton = styled.button`
  border: none;
  background: none;
  cursor: pointer;
`;